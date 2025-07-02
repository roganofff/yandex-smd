package quo.yandex.financialawareness.domain.repository

import quo.yandex.financialawareness.data.models.account.AccountDto
import quo.yandex.financialawareness.network.ResultState

interface AccountRepository {
    suspend fun getAccount() : ResultState<AccountDto>
}