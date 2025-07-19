package quo.yandex.financialawareness.categories.impl.data.repository

import quo.yandex.financialawareness.categories.api.repository.CategoryRepository
import quo.yandex.financialawareness.categories.impl.data.local.dao.CategoryDao
import quo.yandex.financialawareness.categories.impl.data.mapper.CategoryMapper
import quo.yandex.financialawareness.categories.impl.data.remote.pojo.CategoryApi
import quo.yandex.financialawareness.network.util.ApiClient
import quo.yandex.financialawareness.transactions.api.model.CategoryModel
import quo.yandex.financialawareness.util.result.FailureReason
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryApi: CategoryApi,
    private val categoryDao: CategoryDao,
    private val categoryMapper: CategoryMapper,
    private val apiClient: ApiClient,
) : CategoryRepository {

    override suspend fun getCategoriesByType(isIncome: Boolean): Result<List<CategoryModel>> {
        return when (val networkResult = apiClient.call { categoryApi.getCategoriesByType(isIncome) }) {
            is Result.Success -> {
                val domainModels = categoryMapper.mapNetworkToDomainList(networkResult.data)

                val entities = categoryMapper.mapEntityList(domainModels)

                categoryDao.insertAll(entities)

                Result.Success(domainModels)
            }
            is Result.Failure -> {
                if (networkResult.reason is FailureReason.Network) {
                    val cachedCategories = categoryDao.getCategoriesByType(isIncome)

                    if (cachedCategories.isNotEmpty()) {
                        val domainModels = categoryMapper.mapEntityToDomainList(cachedCategories)
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
}