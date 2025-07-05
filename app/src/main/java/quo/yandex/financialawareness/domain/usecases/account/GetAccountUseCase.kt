package quo.yandex.financialawareness.domain.usecases.account

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.data.models.account.AccountDto
import quo.yandex.financialawareness.di.qualifier.IoDispatchers
import quo.yandex.financialawareness.domain.repository.AccountRepository
import quo.yandex.financialawareness.util.result.ResultState
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): ResultState<AccountDto> {
        return withContext(dispatcher) {
            when (val result = accountRepository.getAllAccounts()) {
                is ResultState.Success -> {
                    val account = result.data[0]
                    ResultState.Success(account)
                }

                is ResultState.Failure -> result
            }
        }
    }
}