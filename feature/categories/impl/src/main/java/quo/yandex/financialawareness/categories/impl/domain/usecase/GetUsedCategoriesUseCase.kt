package quo.yandex.financialawareness.categories.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.account.api.model.CategoryDetailsModel
import quo.yandex.financialawareness.account.api.repository.AccountRepository
import quo.yandex.financialawareness.domain.di.qualifier.IoDispatchers
import quo.yandex.financialawareness.transactions.api.model.TransactionType
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject


class GetUsedCategoriesUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(transactionType: TransactionType, query: String): Result<List<CategoryDetailsModel>> {
        return withContext(dispatcher) {
            when (val accountsResult = accountRepository.getAllAccounts()) {
                is Result.Success -> {
                    val categories = mutableListOf<CategoryDetailsModel>()

                    val account = accountsResult.data[0]

                    when (val detailsResult = accountRepository.getAccountDetails(account.id)) {
                        is Result.Success -> {
                            when(transactionType) {
                                TransactionType.EXPENSE -> categories.addAll(detailsResult.data.expenseCategories)
                                TransactionType.INCOME -> categories.addAll(detailsResult.data.incomeCategories)
                                else -> {
                                    categories.addAll(detailsResult.data.expenseCategories)
                                    categories.addAll(detailsResult.data.incomeCategories)
                                }
                            }

                        }

                        is Result.Failure -> return@withContext detailsResult
                    }

                    val filteredCategories = if (query.isBlank()) {
                        categories
                    } else {
                        categories.filter { category ->
                            category.name.contains(query, ignoreCase = true)
                        }
                    }

                    Result.Success(filteredCategories)
                }

                is Result.Failure -> accountsResult
            }
        }
    }
}