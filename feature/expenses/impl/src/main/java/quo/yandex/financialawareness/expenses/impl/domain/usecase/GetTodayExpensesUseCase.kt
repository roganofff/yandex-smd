package quo.yandex.financialawareness.expenses.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.account.api.repository.AccountRepository
import quo.yandex.financialawareness.domain.di.qualifier.IoDispatchers
import quo.yandex.financialawareness.transactions.api.model.TransactionModel
import quo.yandex.financialawareness.transactions.api.repository.TransactionRepository
import quo.yandex.financialawareness.util.DateHelper
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject
import kotlin.collections.get

class GetTodayExpensesUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Result<List<TransactionModel>> {
        return withContext(dispatcher) {
            when (val accountsResult = accountRepository.getAllAccounts()) {
                is Result.Success -> {
                    val transactions = mutableListOf<TransactionModel>()

                    val account = accountsResult.data[0]

                    val todayDate = DateHelper.getTodayApiFormat()

                    when (val transactionsResult =
                        transactionRepository.getTransactionsByAccountAndPeriod(
                            accountId = account.id,
                            startDate = todayDate,
                            endDate = todayDate
                        )) {
                        is Result.Success -> {
                            transactions.addAll(transactionsResult.data)
                        }

                        is Result.Failure -> return@withContext transactionsResult
                    }

                    val filteredCategories = transactions.filter { transaction ->
                        !transaction.category.isIncome
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