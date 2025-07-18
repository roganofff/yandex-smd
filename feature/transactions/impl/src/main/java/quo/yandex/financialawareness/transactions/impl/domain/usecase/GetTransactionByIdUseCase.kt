package quo.yandex.financialawareness.transactions.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.domain.di.qualifier.IoDispatchers
import quo.yandex.financialawareness.transactions.api.model.TransactionModel
import quo.yandex.financialawareness.transactions.api.repository.TransactionRepository
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

class GetTransactionByIdUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(id: Int): Result<TransactionModel> {
        return withContext(dispatcher) {
            when (val result = transactionRepository.getTransactionById(id)) {
                is Result.Success -> {
                    val transaction = result.data
                    Result.Success(transaction)
                }

                is Result.Failure -> result
            }
        }
    }
}