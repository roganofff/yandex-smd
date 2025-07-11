package quo.yandex.financialawareness.domain.repository

import quo.yandex.financialawareness.data.models.account.AccountDetailsDto
import quo.yandex.financialawareness.data.models.account.AccountDto
import quo.yandex.financialawareness.util.result.ResultState

interface AccountRepository {
    suspend fun getAllAccounts() : ResultState<List<AccountDto>>
    suspend fun getAccountDetails(id: Int = 47) : ResultState<AccountDetailsDto>
    suspend fun updateAccount(
        id: Int = 47,
        name: String,
        balance: String,
        currency: String,
    ) : ResultState<AccountDto>
    suspend fun getAllCurrencies() : ResultState<List<String>>
}