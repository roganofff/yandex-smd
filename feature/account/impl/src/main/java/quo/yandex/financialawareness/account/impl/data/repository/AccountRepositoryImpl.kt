package quo.yandex.financialawareness.account.impl.data.repository

import quo.yandex.financialawareness.account.api.model.AccountDetailsModel
import quo.yandex.financialawareness.account.api.model.AccountModel
import quo.yandex.financialawareness.account.api.repository.AccountRepository
import quo.yandex.financialawareness.account.impl.data.local.dao.AccountDao
import quo.yandex.financialawareness.account.impl.data.mapper.AccountDetailsMapper
import quo.yandex.financialawareness.account.impl.data.mapper.AccountMapper
import quo.yandex.financialawareness.account.impl.data.remote.AccountApi
import quo.yandex.financialawareness.account.impl.data.remote.pojo.request.AccountUpdateRequest
import quo.yandex.financialawareness.network.util.ApiClient
import quo.yandex.financialawareness.util.result.FailureReason
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountApi: AccountApi,
    private val accountDao: AccountDao,
    private val accountMapper: AccountMapper,
    private val accountDetailsMapper: AccountDetailsMapper,
    private val apiClient: ApiClient,
): AccountRepository {

    override suspend fun getAllAccounts(): Result<List<AccountModel>> {
        return when (val networkResult = apiClient.call { accountApi.getAllAccounts() }) {
            is Result.Success -> {
                val domainModels = accountMapper.mapNetworkToDomainList(networkResult.data)
                val entities = accountMapper.mapDomainToEntityList(domainModels)
                accountDao.insertAccounts(entities)
                Result.Success(domainModels)
            }
            is Result.Failure -> {
                if (networkResult.reason is FailureReason.Network) {
                    val cachedAccounts = accountDao.getAllAccounts()
                    if (cachedAccounts.isNotEmpty()) {
                        val domainModels = accountMapper.mapEntityToDomainList(cachedAccounts)
                        Result.Success(domainModels)
                    } else {
                        networkResult
                    }
                } else {
                    networkResult
                }
            }
        }
    }

    override suspend fun getAccountDetails(id: Int): Result<AccountDetailsModel> {
        return when (val networkResult = apiClient.call { accountApi.getAccountById(id) }) {
            is Result.Success -> {
                val domainModel = accountDetailsMapper.mapNetworkToDomain(networkResult.data)
                val accountEntity = accountDetailsMapper.mapDomainToEntity(domainModel)
                val categoryEntities = accountDetailsMapper.mapDomainToCategories(id, domainModel)

                accountDao.insertAccountDetailsWithCategories(accountEntity, categoryEntities)
                Result.Success(domainModel)
            }
            is Result.Failure -> {
                if (networkResult.reason is FailureReason.Network) {
                    val cachedAccountDetails = accountDao.getAccountDetailsById(id)
                    if (cachedAccountDetails != null) {
                        val incomeCategories = accountDao.getAccountCategoriesByType(id, true)
                        val expenseCategories = accountDao.getAccountCategoriesByType(id, false)
                        val domainModel = accountDetailsMapper.mapEntityToDomain(
                            cachedAccountDetails,
                            incomeCategories,
                            expenseCategories
                        )
                        Result.Success(domainModel)
                    } else {
                        networkResult
                    }
                } else {
                    networkResult
                }
            }
        }
    }

    override suspend fun updateAccount(
        id: Int,
        name: String,
        balance: String,
        currency: String,
    ): Result<AccountModel> {
        return when (val result = apiClient.call {
            accountApi.updateAccountById(id, AccountUpdateRequest(
                name = name,
                balance = balance,
                currency = currency
            )) }) {
            is Result.Success -> {
                val account = accountMapper.mapNetworkToDomain(result.data)
                val entity = accountMapper.mapDomainToEntity(account)
                accountDao.insertAccounts(listOf(entity))
                Result.Success(account)
            }
            is Result.Failure -> result
        }
    }

    override suspend fun getAllCurrencies(): Result<List<String>> {
        val currencies = listOf("RUB", "USD", "EUR", "GBP", "JPY")
        return Result.Success(currencies)
    }
}