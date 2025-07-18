package quo.yandex.financialawareness.transactions.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.domain.di.qualifier.IoDispatchers
import quo.yandex.financialawareness.transactions.api.model.TransactionModel
import quo.yandex.financialawareness.transactions.api.repository.TransactionRepository
import quo.yandex.financialawareness.util.DateHelper
import quo.yandex.financialawareness.util.result.Result
import java.util.Date
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: Date,
        comment: String?,
    ): Result<TransactionModel> {
        return withContext(dispatcher) {
            when (val result = transactionRepository.createTransaction(
                accountId = accountId,
                categoryId = categoryId,
                amount = amount,
                transactionDate = DateHelper.dateTimeToApiFormat(transactionDate),
                comment = comment,
            )) {
                is Result.Success -> {
                    val transaction = result.data
                    Result.Success(transaction)
                }

                is Result.Failure -> result
            }
        }
    }
}