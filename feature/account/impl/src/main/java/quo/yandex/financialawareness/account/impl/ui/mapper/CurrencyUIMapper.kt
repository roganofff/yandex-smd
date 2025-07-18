package quo.yandex.financialawareness.account.impl.ui.mapper

import quo.yandex.financialawareness.account.impl.ui.model.CurrencyUIModel
import quo.yandex.financialawareness.domain.provider.ResourceProvider
import quo.yandex.financialawareness.ui.util.extension.toCurrencyIconResId
import quo.yandex.financialawareness.ui.util.extension.toCurrencyNameResId
import quo.yandex.financialawareness.ui.util.extension.toCurrencySymbol
import javax.inject.Inject

class CurrencyUIMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {
    fun map(currency: String): CurrencyUIModel {
        return CurrencyUIModel(
            currency = currency,
            name = resourceProvider.getString(currency.toCurrencyNameResId()),
            symbol = currency.toCurrencySymbol(),
            iconResId = currency.toCurrencyIconResId()
        )
    }

    fun mapList(currencies: List<String>): List<CurrencyUIModel> {
        return currencies.map { map(it) }
    }
}