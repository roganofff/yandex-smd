package quo.yandex.financialawareness.transactions.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.domain.di.qualifier.IoDispatchers
import quo.yandex.financialawareness.transactions.api.repository.TransactionRepository
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

class DeleteTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(id: Int): Result<Unit> {
        return withContext(dispatcher) {
            when (val result = transactionRepository.deleteTransactionById(id = id)) {
                is Result.Success -> {
                    Result.Success(result.data)
                }

                is Result.Failure -> result
            }
        }
    }
}