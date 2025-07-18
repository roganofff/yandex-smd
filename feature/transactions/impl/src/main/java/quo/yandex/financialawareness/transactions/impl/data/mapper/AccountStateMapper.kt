package quo.yandex.financialawareness.transactions.impl.data.mapper

import quo.yandex.financialawareness.transactions.api.model.AccountStateModel
import quo.yandex.financialawareness.transactions.impl.data.remote.pojo.response.AccountStateResponse
import javax.inject.Inject

class AccountStateMapper @Inject constructor(){
    fun map(input: AccountStateResponse?): AccountStateModel {
        return input?.let {
            AccountStateModel(
                id = it.id ?: 0,
                name = it.name.orEmpty(),
                balance = input.balance?.toDoubleOrNull() ?: 0.0,
                currency = it.currency.orEmpty(),
                )
        } ?: AccountStateModel()
    }
}