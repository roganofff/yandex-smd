package quo.yandex.financialawareness.transactions.impl.ui.nav

import quo.yandex.financialawareness.navigation.NavGraph
import quo.yandex.financialawareness.transactions.api.model.TransactionType

class TransactionNavGraph : NavGraph() {
    override val route = "transaction"
    override val startDestination = "transaction_main_screen"

    val createDestination = "transaction_create_screen/{transactionType}"
    val updateDestination = "transaction_update_screen/{transactionId}/{transactionType}"

    fun createRoute(transactionType: TransactionType) = "transaction_create_screen/${transactionType.name}"
    fun updateRoute(transactionId: Int, transactionType: TransactionType) =
        "transaction_update_screen/$transactionId/${transactionType.name}"
}