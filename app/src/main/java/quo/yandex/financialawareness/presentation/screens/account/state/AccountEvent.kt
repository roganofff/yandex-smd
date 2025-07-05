package quo.yandex.financialawareness.presentation.screens.account.state

sealed class AccountEvent {
    object LoadAccount : AccountEvent()
    object HideErrorDialog : AccountEvent()
}