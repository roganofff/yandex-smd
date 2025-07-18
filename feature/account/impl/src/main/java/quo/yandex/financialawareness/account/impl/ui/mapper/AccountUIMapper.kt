package quo.yandex.financialawareness.account.impl.ui.mapper

import quo.yandex.financialawareness.account.api.model.AccountModel
import quo.yandex.financialawareness.account.impl.ui.model.AccountUIModel
import quo.yandex.financialawareness.ui.util.extension.formatAmount
import javax.inject.Inject

class AccountUIMapper @Inject constructor(
    private val currencyUIMapper: CurrencyUIMapper
) {
    fun map(account: AccountModel): AccountUIModel {
        return AccountUIModel(
            id = account.id,
            name = account.name,
            balance = account.balance.formatAmount(),
            currency = currencyUIMapper.map(account.currency)
        )
    }
}