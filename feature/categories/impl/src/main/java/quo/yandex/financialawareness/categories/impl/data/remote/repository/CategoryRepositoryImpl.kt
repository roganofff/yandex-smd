package quo.yandex.financialawareness.categories.impl.data.remote.repository

import quo.yandex.financialawareness.categories.api.repository.CategoryRepository
import quo.yandex.financialawareness.categories.impl.data.remote.mapper.CategoryMapper
import quo.yandex.financialawareness.categories.impl.data.remote.remote.CategoryApi
import quo.yandex.financialawareness.network.util.ApiClient
import quo.yandex.financialawareness.transactions.api.model.CategoryModel
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryApi: CategoryApi,
    private val categoryMapper: CategoryMapper,
    private val apiClient: ApiClient,
): CategoryRepository {

    override suspend fun getCategoriesByType(isIncome: Boolean): Result<List<CategoryModel>> {
        return when (val result = apiClient.call { categoryApi.getCategoriesByType(isIncome) }) {
            is Result.Success -> {
                val categories = categoryMapper.mapList(result.data)
                Result.Success(categories)
            }
            is Result.Failure -> result
        }
    }
}
