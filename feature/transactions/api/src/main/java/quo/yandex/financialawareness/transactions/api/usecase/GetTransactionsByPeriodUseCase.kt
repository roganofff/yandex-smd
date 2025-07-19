package quo.yandex.financialawareness.transactions.api.usecase

import quo.yandex.financialawareness.transactions.api.model.TransactionModel
import quo.yandex.financialawareness.transactions.api.model.TransactionType
import quo.yandex.financialawareness.util.result.Result
import java.util.Date

interface GetTransactionsByPeriodUseCase {
    suspend operator fun invoke(
        startDate: Date,
        endDate: Date,
        transactionType: TransactionType = TransactionType.ANY,
    ): Result<List<TransactionModel>>
}