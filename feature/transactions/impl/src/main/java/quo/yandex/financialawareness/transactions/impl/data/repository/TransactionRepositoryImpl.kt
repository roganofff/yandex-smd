package quo.yandex.financialawareness.transactions.impl.data.repository

import quo.yandex.financialawareness.network.util.ApiClient
import quo.yandex.financialawareness.transactions.api.model.TransactionModel
import quo.yandex.financialawareness.transactions.api.repository.TransactionRepository
import quo.yandex.financialawareness.transactions.impl.data.mapper.TransactionMapper
import quo.yandex.financialawareness.transactions.impl.data.remote.TransactionApi
import quo.yandex.financialawareness.transactions.impl.data.remote.pojo.request.TransactionRequest
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionApi: TransactionApi,
    private val transactionMapper: TransactionMapper,
    private val apiClient: ApiClient,
): TransactionRepository {

    override suspend fun getTransactionsByAccountAndPeriod(
        accountId: Int,
        startDate: String?,
        endDate: String?,
    ): Result<List<TransactionModel>> {
        return when (val result = apiClient.call { transactionApi.getTransactionsByAccountAndPeriod(
            accountId = accountId,
            startDate = startDate,
            endDate = endDate
        ) }) {
            is Result.Success -> {
                val transactions = transactionMapper.mapList(result.data)
                Result.Success(transactions)
            }
            is Result.Failure -> result
        }
    }

    override suspend fun getTransactionById(id: Int): Result<TransactionModel> {
        return when (val result = apiClient.call { transactionApi.getTransactionById(id)}) {
            is Result.Success -> {
                val transactions = transactionMapper.map(result.data)
                Result.Success(transactions)
            }
            is Result.Failure -> result
        }
    }

    override suspend fun createTransaction(
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String?,
    ): Result<TransactionModel> {
        return when (val result = apiClient.call { transactionApi.createTransaction(
            TransactionRequest(
                accountId = accountId,
                categoryId = categoryId,
                amount = amount,
                transactionDate = transactionDate,
                comment = comment,
            )
        ) }) {
            is Result.Success -> {
                val transactions = transactionMapper.map(result.data)
                Result.Success(transactions)
            }
            is Result.Failure -> result
        }
    }

    override suspend fun updateTransaction(
        id: Int,
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String?,
    ): Result<TransactionModel> {
        return when (val result = apiClient.call { transactionApi.updateTransaction(
            id = id,
            TransactionRequest(
                accountId = accountId,
                categoryId = categoryId,
                amount = amount,
                transactionDate = transactionDate,
                comment = comment,
            )
        ) }) {
            is Result.Success -> {
                val transactions = transactionMapper.map(result.data)
                Result.Success(transactions)
            }
            is Result.Failure -> result
        }
    }

    override suspend fun deleteTransactionBuId(id: Int): Result<Unit> {
        return when (val result = apiClient.call { transactionApi.deleteTransactionById(id = id) }) {
            is Result.Success -> {
                Result.Success(Unit)
            }
            is Result.Failure -> result
        }
    }
}
