package quo.yandex.financialawareness.domain.repository

import quo.yandex.financialawareness.data.models.transaction.TransactionDetailsDto
import quo.yandex.financialawareness.util.result.ResultState

interface TransactionRepository {
    suspend fun getTransactionsByAccountAndPeriod(
        accountId: Int,
        startDate: String? = null,
        endDate: String? = null
    ): ResultState<List<TransactionDetailsDto>>
}