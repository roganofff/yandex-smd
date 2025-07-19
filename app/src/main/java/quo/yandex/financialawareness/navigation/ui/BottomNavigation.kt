package quo.yandex.financialawareness.navigation.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import quo.yandex.financialawareness.account.impl.ui.nav.AccountNav
import quo.yandex.financialawareness.analysis.impl.ui.nav.AnalysisNav
import quo.yandex.financialawareness.categories.impl.ui.nav.CategoriesNav
import quo.yandex.financialawareness.expenses.impl.ui.nav.ExpensesNav
import quo.yandex.financialawareness.income.impl.ui.nav.IncomeNav
import quo.yandex.financialawareness.navigation.BottomNavIds
import quo.yandex.financialawareness.navigation.BottomNavItem
import quo.yandex.financialawareness.navigation.NavigationManager
import quo.yandex.financialawareness.settings.impl.ui.nav.SettingsNav
import quo.yandex.financialawareness.transactions.api.model.TransactionType
import quo.yandex.financialawareness.transactions.impl.ui.nav.TransactionNav
import quo.yandex.financialawareness.ui.R

@Composable
fun BottomNavigationBar(
    bottomNavItems: List<BottomNavItem>,
    navController: NavController
) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    val transactionTypeUsed = (currentRoute?.startsWith(TransactionNav.navGraph.route) == true
            || currentRoute?.startsWith(AnalysisNav.navGraph.route) == true)

    val transactionType = if(transactionTypeUsed)  {
        val transactionTypeArg = navController.currentBackStackEntry
            ?.arguments?.getString("transactionType")
        TransactionType.fromString(transactionTypeArg ?: "")
    } else null

    NavigationBar {
        bottomNavItems.forEach { item ->
            val isSelected = when {
                transactionTypeUsed -> {
                    when (transactionType) {
                        TransactionType.INCOME -> item.id == BottomNavIds.Income.id
                        TransactionType.EXPENSE -> item.id == BottomNavIds.Expenses.id
                        else -> false
                    }
                }
                item.id == BottomNavIds.Account.id -> currentRoute?.startsWith(AccountNav.navGraph.route) == true
                item.id == BottomNavIds.Income.id -> currentRoute?.startsWith(IncomeNav.navGraph.route) == true
                item.id == BottomNavIds.Settings.id -> currentRoute?.startsWith(SettingsNav.navGraph.route) == true
                item.id == BottomNavIds.Expenses.id -> currentRoute?.startsWith(ExpensesNav.navGraph.route) == true
                item.id == BottomNavIds.Categories.id -> currentRoute?.startsWith(CategoriesNav.navGraph.route) == true
                else -> false
            }

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    item.onClick()
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResourceId),
                        contentDescription = stringResource(id = item.labelResourceId),
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.labelResourceId),
                        maxLines = 1
                    )
                },
                alwaysShowLabel = true,
            )
        }
    }
}

/**
 * Создает и запоминает список элементов нижней навигационной панели.
 *
 * Использует remember для кэширования списка элементов, чтобы избежать
 * ненужных пересозданий при перекомпозиции. Список обновляется только
 * при изменении navController или navigationManager.
 *
 * @param navController Контроллер навигации для выполнения переходов
 * @param navigationManager Менеджер навигации, содержащий логику навигации
 * @return Список элементов нижней навигационной панели
 */


@Composable
fun rememberBottomNavItems(
    navController: NavController,
    navigationManager: NavigationManager
): List<BottomNavItem> {
    return remember(navController, navigationManager) {
        listOf(
            BottomNavItem(
                id = BottomNavIds.Expenses.id,
                onClick = { navigationManager.navigateToExpenses(navController) },
                iconResourceId = R.drawable.ic_expenses,
                labelResourceId = R.string.expenses
            ),
            BottomNavItem(
                id = BottomNavIds.Income.id,
                onClick = { navigationManager.navigateToIncome(navController) },
                iconResourceId = R.drawable.ic_income,
                labelResourceId = R.string.income
            ),
            BottomNavItem(
                id = BottomNavIds.Account.id,
                onClick = { navigationManager.navigateToAccount(navController) },
                iconResourceId = R.drawable.ic_account,
                labelResourceId = R.string.account
            ),
            BottomNavItem(
                id = BottomNavIds.Categories.id,
                onClick = { navigationManager.navigateToCategories(navController) },
                iconResourceId = R.drawable.ic_stats,
                labelResourceId = R.string.stats
            ),
            BottomNavItem(
                id = BottomNavIds.Settings.id,
                onClick = { navigationManager.navigateToSettings(navController) },
                iconResourceId = R.drawable.ic_settings,
                labelResourceId = R.string.settings
            )
        )
    }
}