package quo.yandex.financialawareness.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.screens.AccountScreen
import quo.yandex.financialawareness.presentation.screens.CategoriesScreen
import quo.yandex.financialawareness.presentation.screens.ExpensesScreen
import quo.yandex.financialawareness.presentation.screens.IncomeScreen
import quo.yandex.financialawareness.presentation.screens.SettingsScreen

enum class Destination(
    val route: String,
    val label: String,
    val icon: Int,
    val contentDescription: String
) {
    EXPENSES("expanses", "Расходы", R.drawable.downtrend, "Расходы"),
    INCOME("income", "Доходы", R.drawable.uptrend, "Доходы"),
    ACCOUNT("account", "Счёт", R.drawable.calculator, "Счёт"),
    CATEGORIES("categories", "Статьи", R.drawable.bar_chart_side, "Статьи"),
    SETTINGS("settings", "Настройки", R.drawable.settings, "Настройки"),
}


@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.EXPENSES -> ExpensesScreen(modifier)
                    Destination.INCOME -> IncomeScreen(modifier)
                    Destination.ACCOUNT -> AccountScreen(modifier)
                    Destination.CATEGORIES -> CategoriesScreen(modifier)
                    Destination.SETTINGS -> SettingsScreen(modifier)
                }
            }
        }
    }
}
