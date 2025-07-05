package quo.yandex.financialawareness.domain.api

import quo.yandex.financialawareness.data.models.account.AccountDetailsDto
import quo.yandex.financialawareness.data.models.account.AccountDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountApi {
    @GET("accounts/{id}")
    suspend fun getAccountById(@Path("id") id: Int): AccountDetailsDto

    @GET("accounts")
    suspend fun getAccounts(): List<AccountDto>
}