package quo.yandex.financialawareness.account.impl.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import quo.yandex.financialawareness.account.impl.data.remote.pojo.request.AccountUpdateRequest
import quo.yandex.financialawareness.account.impl.data.remote.pojo.response.AccountDetailsResponse
import quo.yandex.financialawareness.account.impl.data.remote.pojo.response.AccountResponse

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
