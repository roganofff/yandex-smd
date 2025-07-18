package quo.yandex.financialawareness.account.impl.ui.nav

import quo.yandex.financialawareness.navigation.NavGraph

class AccountNavGraph : NavGraph() {
    override val route = "account"
    override val startDestination = "account_main_screen"
    val updateDestination = "account_update_screen"
}