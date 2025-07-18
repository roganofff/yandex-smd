package quo.yandex.financialawareness.transactions.impl.ui.screen.update.state

sealed class TransactionUpdateEffect {
    object TransactionUpdated : TransactionUpdateEffect()
    object TransactionDeleted : TransactionUpdateEffect()
    data class ShowToast(val message: String) : TransactionUpdateEffect()
}