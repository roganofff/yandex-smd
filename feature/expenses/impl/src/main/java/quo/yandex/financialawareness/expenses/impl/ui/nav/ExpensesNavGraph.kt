package quo.yandex.financialawareness.expenses.impl.ui.nav

import quo.yandex.financialawareness.navigation.NavGraph

class ExpensesNavGraph : NavGraph() {
    override val route = "expenses"
    override val startDestination = "expenses_today_screen"
    val historyDestination = "expenses_history_screen"
}