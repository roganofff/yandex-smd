package quo.yandex.financialawareness.data.repository

import quo.yandex.financialawareness.data.api.ApiClient
import quo.yandex.financialawareness.data.api.request.AccountUpdateRequest
import quo.yandex.financialawareness.data.mappers.AccountDetailsMapper
import quo.yandex.financialawareness.data.mappers.AccountMapper
import quo.yandex.financialawareness.data.models.account.AccountDetailsDto
import quo.yandex.financialawareness.data.models.account.AccountDto
import quo.yandex.financialawareness.domain.api.AccountApi
import quo.yandex.financialawareness.domain.repository.AccountRepository
import quo.yandex.financialawareness.util.result.ResultState
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountApi: AccountApi,
    private val accountMapper: AccountMapper,
    private val accountDetailsMapper: AccountDetailsMapper,
    private val apiClient: ApiClient,
) : AccountRepository {

    override suspend fun getAllAccounts(): ResultState<List<AccountDto>> {
        return when (val result = apiClient.call { accountApi.getAllAccounts() }) {
            is ResultState.Success -> {
                val accounts = accountMapper.mapList(result.data)
                ResultState.Success(accounts)
            }

            is ResultState.Failure -> result
        }
    }

    override suspend fun getAccountDetails(id: Int): ResultState<AccountDetailsDto> {
        return when (val result = apiClient.call { accountApi.getAccountById(id) }) {
            is ResultState.Success -> {
                val accountDetails = accountDetailsMapper.map(result.data)
                ResultState.Success(accountDetails)
            }

            is ResultState.Failure -> result
        }
    }

    override suspend fun updateAccount(
        id: Int,
        name: String,
        balance: String,
        currency: String,
    ): ResultState<AccountDto> {
        return when (val result = apiClient.call {
            accountApi.updateAccountById(
                id, AccountUpdateRequest(
                    name = name,
                    balance = balance,
                    currency = currency
                )
            )
        }) {
            is ResultState.Success -> {
                val account = accountMapper.map(result.data)
                ResultState.Success(account)
            }

            is ResultState.Failure -> result
        }
    }

    override suspend fun getAllCurrencies(): ResultState<List<String>> {
        val currencies = listOf("RUB", "USD", "EUR", "GBP", "JPY")
        return ResultState.Success(currencies)
    }
}