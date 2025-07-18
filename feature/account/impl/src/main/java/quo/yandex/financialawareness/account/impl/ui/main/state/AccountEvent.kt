package quo.yandex.financialawareness.account.impl.ui.main.state

sealed class AccountEvent {
    object LoadAccount : AccountEvent()
    object HideErrorDialog : AccountEvent()
}
