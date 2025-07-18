package quo.yandex.financialawareness.account.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.account.api.repository.AccountRepository
import quo.yandex.financialawareness.domain.di.qualifier.IoDispatchers
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

class GetAllCurrenciesUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Result<List<String>> {
        return withContext(dispatcher) {
            when (val result = accountRepository.getAllCurrencies()) {
                is Result.Success -> {
                    result
                }

                is Result.Failure -> result
            }
        }
    }
}