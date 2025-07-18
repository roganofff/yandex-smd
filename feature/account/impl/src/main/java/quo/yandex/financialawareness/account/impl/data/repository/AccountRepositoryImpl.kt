package quo.yandex.financialawareness.account.impl.data.repository

import quo.yandex.financialawareness.account.api.model.AccountDetailsModel
import quo.yandex.financialawareness.account.api.model.AccountModel
import quo.yandex.financialawareness.account.api.repository.AccountRepository
import quo.yandex.financialawareness.account.impl.data.mapper.AccountDetailsMapper
import quo.yandex.financialawareness.account.impl.data.mapper.AccountMapper
import quo.yandex.financialawareness.account.impl.data.remote.AccountApi
import quo.yandex.financialawareness.account.impl.data.remote.pojo.request.AccountUpdateRequest
import quo.yandex.financialawareness.network.util.ApiClient
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountApi: AccountApi,
    private val accountMapper: AccountMapper,
    private val accountDetailsMapper: AccountDetailsMapper,
    private val apiClient: ApiClient,
): AccountRepository {

    override suspend fun getAllAccounts(): Result<List<AccountModel>> {
        return when (val result = apiClient.call { accountApi.getAllAccounts() }) {
            is Result.Success -> {
                val accounts = accountMapper.mapList(result.data)
                Result.Success(accounts)
            }
            is Result.Failure -> result
        }
    }

    override suspend fun getAccountDetails(id: Int): Result<AccountDetailsModel> {
        return when (val result = apiClient.call { accountApi.getAccountById(id) }) {
            is Result.Success -> {
                val accountDetails = accountDetailsMapper.map(result.data)
                Result.Success(accountDetails)
            }
            is Result.Failure -> result
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
                val account = accountMapper.map(result.data)
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
