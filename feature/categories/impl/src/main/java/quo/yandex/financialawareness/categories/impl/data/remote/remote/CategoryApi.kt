package quo.yandex.financialawareness.categories.impl.data.remote.remote

import retrofit2.http.GET
import retrofit2.http.Path
import quo.yandex.financialawareness.categories.impl.data.remote.remote.pojo.response.CategoryResponse

interface CategoryApi {

    @GET("categories/type/{isIncome}")
    suspend fun getCategoriesByType(
        @Path("isIncome") isIncome: Boolean,
    ): List<CategoryResponse>?
}
