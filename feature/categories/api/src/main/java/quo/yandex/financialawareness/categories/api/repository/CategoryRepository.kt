package quo.yandex.financialawareness.categories.api.repository

import quo.yandex.financialawareness.transactions.api.model.CategoryModel
import quo.yandex.financialawareness.util.result.Result

interface CategoryRepository {
    suspend fun getCategoriesByType(isIncome: Boolean = false): Result<List<CategoryModel>>
}