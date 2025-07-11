package quo.yandex.financialawareness.domain.api

import quo.yandex.financialawareness.data.api.request.AccountUpdateRequest
import quo.yandex.financialawareness.data.api.response.AccountDetailsResponse
import quo.yandex.financialawareness.data.api.response.AccountResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface AccountApi {

    @GET("accounts")
    suspend fun getAllAccounts(): List<AccountResponse>?

    @GET("accounts/{id}")
    suspend fun getAccountById(@Path("id") accountId: Int): AccountDetailsResponse?

    @PUT("accounts/{id}")
    suspend fun updateAccountById(
        @Path("id") accountId: Int,
        @Body updateAccountRequest: AccountUpdateRequest
    ): AccountResponse?
}