package quo.yandex.financialawareness.domain.usecases.account

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.di.qualifier.IoDispatchers
import quo.yandex.financialawareness.domain.repository.AccountRepository
import quo.yandex.financialawareness.util.result.ResultState
import javax.inject.Inject

class GetAllCurrenciesUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): ResultState<List<String>> {
        return withContext(dispatcher) {
            when (val result = accountRepository.getAllCurrencies()) {
                is ResultState.Success -> {
                    result
                }

                is ResultState.Failure -> result
            }
        }
    }
}