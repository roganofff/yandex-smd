package quo.yandex.financialawareness.transactions.impl.data.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import quo.yandex.financialawareness.transactions.impl.data.remote.pojo.request.TransactionRequest
import quo.yandex.financialawareness.transactions.impl.data.remote.pojo.response.TransactionResponse

interface TransactionApi {

    @GET("transactions/account/{accountId}/period")
    suspend fun getTransactionsByAccountAndPeriod(
        @Path("accountId") accountId: Int,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): List<TransactionResponse>?

    @GET("transactions/{id}")
    suspend fun getTransactionById(
        @Path("id") id: Int,
    ): TransactionResponse?

    @POST("transactions")
    suspend fun createTransaction(
        @Body transactionRequest: TransactionRequest
    ): TransactionResponse?

    @PUT("transactions/{id}")
    suspend fun updateTransaction(
        @Path("id") id: Int,
        @Body transactionRequest: TransactionRequest
    ): TransactionResponse?

    @DELETE("transactions/{id}")
    suspend fun deleteTransactionById(@Path("id") id: Int)
}