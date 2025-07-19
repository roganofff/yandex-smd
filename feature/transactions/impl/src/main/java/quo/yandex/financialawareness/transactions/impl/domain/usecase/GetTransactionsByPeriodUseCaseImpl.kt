package quo.yandex.financialawareness.transactions.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.account.api.repository.AccountRepository
import quo.yandex.financialawareness.domain.di.qualifier.IoDispatchers
import quo.yandex.financialawareness.transactions.api.model.TransactionModel
import quo.yandex.financialawareness.transactions.api.model.TransactionType
import quo.yandex.financialawareness.transactions.api.repository.TransactionRepository
import quo.yandex.financialawareness.transactions.api.usecase.GetTransactionsByPeriodUseCase
import quo.yandex.financialawareness.util.DateHelper
import quo.yandex.financialawareness.util.result.Result
import java.util.Date
import javax.inject.Inject


class GetTransactionsByPeriodUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) : GetTransactionsByPeriodUseCase {
    override suspend operator fun invoke(
        startDate: Date,
        endDate: Date,
        transactionType: TransactionType
    ): Result<List<TransactionModel>> {
        return withContext(dispatcher) {
            when (val accountsResult = accountRepository.getAllAccounts()) {
                is Result.Success -> {
                    val transactions = mutableListOf<TransactionModel>()

                    val account = accountsResult.data[0]

                    val startDate = DateHelper.dateToApiFormat(startDate)
                    val endDate = DateHelper.dateToApiFormat(endDate)

                    when (val transactionsResult =
                        transactionRepository.getTransactionsByAccountAndPeriod(
                            accountId = account.id,
                            startDate = startDate,
                            endDate = endDate
                        )) {
                        is Result.Success -> {
                            transactions.addAll(transactionsResult.data)
                        }

                        is Result.Failure -> return@withContext transactionsResult
                    }

                    val filteredCategories = transactions.filter { transaction ->
                        when(transactionType) {
                            TransactionType.INCOME -> transaction.category.isIncome
                            TransactionType.EXPENSE -> !transaction.category.isIncome
                            TransactionType.ANY -> true
                        }
                    }.sortedBy {
                            transaction -> transaction.transactionDate
                    }.reversed()

                    Result.Success(filteredCategories)
                }

                is Result.Failure -> accountsResult
            }
        }
    }
}