package quo.yandex.financialawareness.categories.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import quo.yandex.financialawareness.categories.api.repository.CategoryRepository
import quo.yandex.financialawareness.categories.api.usecase.GetCategoriesUseCase
import quo.yandex.financialawareness.domain.di.qualifier.IoDispatchers
import quo.yandex.financialawareness.transactions.api.model.CategoryModel
import quo.yandex.financialawareness.transactions.api.model.TransactionType
import quo.yandex.financialawareness.util.result.FailureReason
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject


class GetCategoriesUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) : GetCategoriesUseCase {
    override suspend operator fun invoke(transactionType: TransactionType): Result<List<CategoryModel>> {
        return withContext(dispatcher) {
            when (transactionType) {
                TransactionType.INCOME -> categoryRepository.getCategoriesByType(isIncome = true)
                TransactionType.EXPENSE -> categoryRepository.getCategoriesByType(isIncome = false)
                TransactionType.ANY -> {
                    val incomeResult = categoryRepository.getCategoriesByType(isIncome = true)
                    val expenseResult = categoryRepository.getCategoriesByType(isIncome = false)

                    when {
                        incomeResult is Result.Success && expenseResult is Result.Success -> {
                            Result.Success(incomeResult.data + expenseResult.data)
                        }
                        incomeResult is Result.Failure -> incomeResult
                        expenseResult is Result.Failure -> expenseResult
                        else -> Result.Failure(FailureReason.Unknown())
                    }
                }
            }
        }
    }
}