package quo.yandex.financialawareness.account.impl.ui.update.state

sealed class AccountUpdateEffect {
    object AccountUpdated : AccountUpdateEffect()
    data class ShowToast(val message: String) : AccountUpdateEffect()
}
