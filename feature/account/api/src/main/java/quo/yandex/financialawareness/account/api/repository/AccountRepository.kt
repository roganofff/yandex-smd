package quo.yandex.financialawareness.account.api.repository

import quo.yandex.financialawareness.account.api.model.AccountDetailsModel
import quo.yandex.financialawareness.account.api.model.AccountModel
import quo.yandex.financialawareness.util.result.Result


interface AccountRepository {
    suspend fun getAllAccounts() : Result<List<AccountModel>>
    suspend fun getAccountDetails(id: Int) : Result<AccountDetailsModel>
    suspend fun updateAccount(
        id: Int,
        name: String,
        balance: String,
        currency: String,
    ) : Result<AccountModel>
    suspend fun getAllCurrencies() : Result<List<String>>
}