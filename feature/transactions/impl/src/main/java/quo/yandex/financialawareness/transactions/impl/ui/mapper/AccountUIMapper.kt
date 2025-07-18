package quo.yandex.financialawareness.transactions.impl.ui.mapper

import quo.yandex.financialawareness.account.api.model.AccountModel
import quo.yandex.financialawareness.transactions.impl.ui.model.AccountUIModel
import quo.yandex.financialawareness.ui.util.extension.toCurrencySymbol
import javax.inject.Inject

class AccountUIMapper @Inject constructor() {
    fun map(category: AccountModel): AccountUIModel {
        return AccountUIModel(
            id = category.id,
            name = category.name,
            currencySymbol = category.currency.toCurrencySymbol()
        )
    }
}