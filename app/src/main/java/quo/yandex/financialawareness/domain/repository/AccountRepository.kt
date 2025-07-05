package quo.yandex.financialawareness.domain.repository

import quo.yandex.financialawareness.data.models.account.AccountDetailsDto
import quo.yandex.financialawareness.data.models.account.AccountDto
import quo.yandex.financialawareness.util.result.ResultState

interface AccountRepository {
    suspend fun getAccountDetails(id: Int) : ResultState<AccountDetailsDto>
    suspend fun getAllAccounts() : ResultState<List<AccountDto>>
}