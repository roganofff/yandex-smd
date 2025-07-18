package quo.yandex.financialawareness.account.impl.ui.update.state

import quo.yandex.financialawareness.account.impl.ui.model.CurrencyUIModel

sealed class AccountUpdateEvent {
    object LoadData : AccountUpdateEvent()
    object OnDoneClicked : AccountUpdateEvent()
    object HideErrorDialog : AccountUpdateEvent()
    object HideCurrencyBottomSheet : AccountUpdateEvent()
    object ShowCurrencyBottomSheet : AccountUpdateEvent()
    data class SelectCurrency(val currency: CurrencyUIModel) : AccountUpdateEvent()
    data class UpdateName(val name: String) : AccountUpdateEvent()
    data class UpdateBalance(val balance: String) : AccountUpdateEvent()
}
