package quo.yandex.financialawareness.transactions.api.repository

import quo.yandex.financialawareness.transactions.api.model.TransactionModel
import quo.yandex.financialawareness.util.result.Result

interface TransactionRepository {
    suspend fun getTransactionsByAccountAndPeriod(
        accountId: Int,
        startDate: String? = null,
        endDate: String? = null
    ): Result<List<TransactionModel>>

    suspend fun getTransactionById(id: Int) : Result<TransactionModel>

    suspend fun createTransaction(
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String? = null,
    ) : Result<TransactionModel>

    suspend fun updateTransaction(
        id: Int,
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String? = null,
    ) : Result<TransactionModel>

    suspend fun deleteTransactionById(id: Int) : Result<Unit>
}