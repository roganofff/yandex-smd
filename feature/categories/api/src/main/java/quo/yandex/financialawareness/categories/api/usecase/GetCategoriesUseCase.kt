package quo.yandex.financialawareness.categories.api.usecase

import quo.yandex.financialawareness.transactions.api.model.CategoryModel
import quo.yandex.financialawareness.transactions.api.model.TransactionType
import quo.yandex.financialawareness.util.result.Result

interface GetCategoriesUseCase {
    suspend operator fun invoke(
        transactionType: TransactionType = TransactionType.ANY,
    ): Result<List<CategoryModel>>
}