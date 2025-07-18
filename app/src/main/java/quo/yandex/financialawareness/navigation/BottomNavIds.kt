package quo.yandex.financialawareness.navigation

import quo.yandex.financialawareness.account.impl.ui.nav.AccountNav
import quo.yandex.financialawareness.categories.impl.ui.nav.CategoriesNav
import quo.yandex.financialawareness.expenses.impl.ui.nav.ExpensesNav
import quo.yandex.financialawareness.income.impl.ui.nav.IncomeNav
import quo.yandex.financialawareness.settings.impl.ui.nav.SettingsNav

sealed class BottomNavIds(val id: String) {
    object Account : BottomNavIds(AccountNav.navGraph.route)
    object Income : BottomNavIds(IncomeNav.navGraph.route)
    object Settings : BottomNavIds(SettingsNav.navGraph.route)
    object Expenses : BottomNavIds(ExpensesNav.navGraph.route)
    object Categories : BottomNavIds(CategoriesNav.navGraph.route)

    companion object {
        val all = listOf(Account, Income, Settings, Expenses, Categories)
    }
}