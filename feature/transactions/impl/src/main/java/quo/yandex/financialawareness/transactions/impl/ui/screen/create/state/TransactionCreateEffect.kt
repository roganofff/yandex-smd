package quo.yandex.financialawareness.transactions.impl.ui.screen.create.state

sealed class TransactionCreateEffect {
    object TransactionCreated : TransactionCreateEffect()
    data class ShowToast(val message: String) : TransactionCreateEffect()
}