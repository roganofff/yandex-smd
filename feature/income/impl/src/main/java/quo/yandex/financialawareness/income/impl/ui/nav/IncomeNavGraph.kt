package quo.yandex.financialawareness.income.impl.ui.nav

import quo.yandex.financialawareness.navigation.NavGraph

class IncomeNavGraph : NavGraph() {
    override val route = "income"
    override val startDestination = "income_today_screen"
    val historyDestination = "income_history_screen"
}