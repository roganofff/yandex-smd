package quo.yandex.financialawareness.data.mappers

import quo.yandex.financialawareness.di.provider.ResourceProvider
import quo.yandex.financialawareness.presentation.screens.account.model.CurrencyUIModel
import quo.yandex.financialawareness.util.extension.toCurrencyIconResId
import quo.yandex.financialawareness.util.extension.toCurrencyNameResId
import quo.yandex.financialawareness.util.extension.toCurrencySymbol
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