package quo.yandex.financialawareness.presentation.screens.account.state

sealed class AccountUpdateEffect {
    object AccountUpdated : AccountUpdateEffect()
    data class ShowToast(val message: String) : AccountUpdateEffect()
}
