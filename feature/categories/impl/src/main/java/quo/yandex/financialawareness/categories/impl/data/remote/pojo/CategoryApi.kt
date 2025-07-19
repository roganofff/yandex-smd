package quo.yandex.financialawareness.categories.impl.data.remote.pojo

import quo.yandex.financialawareness.categories.impl.data.remote.pojo.response.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryApi {

    @GET("categories/type/{isIncome}")
    suspend fun getCategoriesByType(
        @Path("isIncome") isIncome: Boolean,
    ): List<CategoryResponse>?
}