package quo.yandex.financialawareness.transactions.impl.data.repository

import quo.yandex.financialawareness.network.util.ApiClient
import quo.yandex.financialawareness.transactions.api.model.TransactionModel
import quo.yandex.financialawareness.transactions.api.repository.TransactionRepository
import quo.yandex.financialawareness.transactions.impl.data.local.dao.TransactionDao
import quo.yandex.financialawareness.transactions.impl.data.mapper.TransactionMapper
import quo.yandex.financialawareness.transactions.impl.data.remote.TransactionApi
import quo.yandex.financialawareness.transactions.impl.data.remote.pojo.request.TransactionRequest
import quo.yandex.financialawareness.util.DateHelper
import quo.yandex.financialawareness.util.result.FailureReason
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionApi: TransactionApi,
    private val transactionDao: TransactionDao,
    private val transactionMapper: TransactionMapper,
    private val apiClient: ApiClient,
): TransactionRepository {

    override suspend fun getTransactionsByAccountAndPeriod(
        accountId: Int,
        startDate: String?,
        endDate: String?,
    ): Result<List<TransactionModel>> {
        return when (val networkResult = apiClient.call {
            transactionApi.getTransactionsByAccountAndPeriod(
                accountId = accountId,
                startDate = startDate,
                endDate = endDate
            )
        }) {
            is Result.Success -> {
                val domainModels = transactionMapper.mapNetworkToDomainList(networkResult.data)
                val entities = transactionMapper.mapDomainToEntityList(domainModels)

                transactionDao.deleteTransactionsByAccount(accountId)
                transactionDao.insertTransactions(entities)

                Result.Success(domainModels)
            }
            is Result.Failure -> {
                if (networkResult.reason is FailureReason.Network) {
                    val startTimestamp = startDate?.let { DateHelper.parseApiDateTime(it).time }
                    val endTimestamp = endDate?.let { DateHelper.parseApiDateTime(it).time }

                    val cachedTransactions = transactionDao.getTransactionsByAccountAndPeriod(
                        accountId, startTimestamp, endTimestamp
                    )

                    if (cachedTransactions.isNotEmpty()) {
                        val domainModels = transactionMapper.mapEntityToDomainList(cachedTransactions)
                        Result.Success(domainModels)
                    } else {
                        networkResult
                    }
                } else {
                    networkResult
                }
            }
        }
    }

    override suspend fun getTransactionById(id: Int): Result<TransactionModel> {
        return when (val networkResult = apiClient.call { transactionApi.getTransactionById(id) }) {
            is Result.Success -> {
                val domainModel = transactionMapper.mapNetworkToDomain(networkResult.data)
                val entity = transactionMapper.mapDomainToEntity(domainModel)
                transactionDao.insertTransaction(entity)
                Result.Success(domainModel)
            }
            is Result.Failure -> {
                if (networkResult.reason is FailureReason.Network) {
                    val cachedTransaction = transactionDao.getTransactionById(id)
                    if (cachedTransaction != null) {
                        val domainModel = transactionMapper.mapEntityToDomain(cachedTransaction)
                        Result.Success(domainModel)
                    } else {
                        networkResult
                    }
                } else {
                    networkResult
                }
            }
        }
    }

    override suspend fun createTransaction(
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String?,
    ): Result<TransactionModel> {
        return when (val result = apiClient.call {
            transactionApi.createTransaction(
                TransactionRequest(
                    accountId = accountId,
                    categoryId = categoryId,
                    amount = amount,
                    transactionDate = transactionDate,
                    comment = comment,
                )
            )
        }) {
            is Result.Success -> {
                val domainModel = transactionMapper.mapNetworkToDomain(result.data)
                val entity = transactionMapper.mapDomainToEntity(domainModel)
                transactionDao.insertTransaction(entity)
                Result.Success(domainModel)
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
        return when (val result = apiClient.call {
            transactionApi.updateTransaction(
                id = id,
                TransactionRequest(
                    accountId = accountId,
                    categoryId = categoryId,
                    amount = amount,
                    transactionDate = transactionDate,
                    comment = comment,
                )
            )
        }) {
            is Result.Success -> {
                val domainModel = transactionMapper.mapNetworkToDomain(result.data)
                val entity = transactionMapper.mapDomainToEntity(domainModel)
                transactionDao.insertTransaction(entity)
                Result.Success(domainModel)
            }
            is Result.Failure -> result
        }
    }

    override suspend fun deleteTransactionById(id: Int): Result<Unit> {
        return when (val result = apiClient.call { transactionApi.deleteTransactionById(id = id) }) {
            is Result.Success -> {
                transactionDao.deleteTransactionById(id)
                Result.Success(Unit)
            }
            is Result.Failure -> result
        }
    }
}