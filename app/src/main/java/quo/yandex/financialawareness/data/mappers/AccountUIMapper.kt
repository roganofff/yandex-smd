package quo.yandex.financialawareness.data.mappers

import quo.yandex.financialawareness.data.models.account.AccountDto
import quo.yandex.financialawareness.presentation.screens.account.model.AccountUIModel
import quo.yandex.financialawareness.util.extension.formatAmount
import quo.yandex.financialawareness.util.extension.toCurrencySymbol
import javax.inject.Inject

class AccountUIMapper @Inject constructor() {
    fun map(account: AccountDto): AccountUIModel {
        return AccountUIModel(
            id = account.id,
            name = account.name,
            balance = "${account.balance.formatAmount()} ${account.currency.toCurrencySymbol()}",
            currency = account.currency.toCurrencySymbol()
        )
    }
}