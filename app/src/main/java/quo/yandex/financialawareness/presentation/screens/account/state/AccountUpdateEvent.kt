package quo.yandex.financialawareness.presentation.screens.account.state

import quo.yandex.financialawareness.presentation.screens.account.model.CurrencyUIModel


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
