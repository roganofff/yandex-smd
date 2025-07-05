package quo.yandex.financialawareness.domain.api

import quo.yandex.financialawareness.data.models.account.AccountDetailsDto
import quo.yandex.financialawareness.data.models.account.AccountDto
import quo.yandex.financialawareness.data.models.category.CategoriesItemDto
import quo.yandex.financialawareness.data.models.transaction.TransactionDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FinancialApi {


    @GET("transactions/account/{accountId}/period")
    suspend fun getTransactionsForPeriod(
        @Path("accountId") accountId: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<TransactionDetailsDto>

    @GET("categories")
    suspend fun getAllCategories(): List<CategoriesItemDto>
}