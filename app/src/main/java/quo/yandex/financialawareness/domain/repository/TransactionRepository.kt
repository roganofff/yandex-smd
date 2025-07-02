package quo.yandex.financialawareness.domain.repository

import quo.yandex.financialawareness.data.models.transaction.TransactionDetailsDto
import quo.yandex.financialawareness.data.models.transaction.TransactionDto
import quo.yandex.financialawareness.network.ResultState

interface TransactionRepository {
    suspend fun getHistory(startDate: String, endDate: String): ResultState<List<TransactionDetailsDto>>

    suspend fun getExpensesTransactions(): ResultState<List<TransactionDetailsDto>>

    suspend fun getIncomeTransactions() : ResultState<List<TransactionDetailsDto>>

}