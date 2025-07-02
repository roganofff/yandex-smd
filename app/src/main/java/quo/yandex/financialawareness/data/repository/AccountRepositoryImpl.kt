package quo.yandex.financialawareness.data.repository

import dagger.hilt.android.AndroidEntryPoint
import quo.yandex.financialawareness.data.models.account.AccountDto
import quo.yandex.financialawareness.domain.api.FinancialApi
import quo.yandex.financialawareness.domain.repository.AccountRepository
import quo.yandex.financialawareness.network.ResultState
import quo.yandex.financialawareness.network.safeNetworkCall
import quo.yandex.financialawareness.network.resolveCurrentAccountId
import javax.inject.Inject

@AndroidEntryPoint
class AccountRepositoryImpl @Inject constructor(
    private val api: FinancialApi
) : AccountRepository {
    override suspend fun getAccount(): ResultState<AccountDto> {
        return safeNetworkCall {
            val accountId = resolveCurrentAccountId(api)
            api.getAccountById(accountId)
        }
    }
}