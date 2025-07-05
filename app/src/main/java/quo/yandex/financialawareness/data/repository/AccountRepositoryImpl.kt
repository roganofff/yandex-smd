package quo.yandex.financialawareness.data.repository

import quo.yandex.financialawareness.data.models.account.AccountDetailsDto
import quo.yandex.financialawareness.data.models.account.AccountDto
import quo.yandex.financialawareness.domain.api.AccountApi
import quo.yandex.financialawareness.domain.api.FinancialApi
import quo.yandex.financialawareness.domain.repository.AccountRepository
import quo.yandex.financialawareness.network.resolveCurrentAccountId
import quo.yandex.financialawareness.util.result.FailureReason
import quo.yandex.financialawareness.util.result.ResultState
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountApi: AccountApi,
    private val accountMapper: AccountMapper,
    private val accountDetailsMapper: AccountDetailsMapper,
    private val apiClient: ApiClient,
): AccountRepository {

    override suspend fun getAllAccounts(): Result<List<AccountDto>> {
        return when (val result = apiClient.call { accountApi.getAllAccounts() }) {
            is Result.Success -> {
                val accounts = accountMapper.mapList(result.data)
                Result.Success(accounts)
            }
            is Result.Failure -> result
        }
    }

    override suspend fun getAccountDetails(id: Int): Result<AccountDetailsDto> {
        return when (val result = apiClient.call { accountApi.getAccountById(id) }) {
            is Result.Success -> {
                val accountDetails = accountDetailsMapper.map(result.data)
                Result.Success(accountDetails)
            }
            is Result.Failure -> result
        }
    }
}