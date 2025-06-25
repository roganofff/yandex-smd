package quo.yandex.financialawareness.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.screens.account.AccountScreen
import quo.yandex.financialawareness.presentation.screens.categories.CategoriesScreen
import quo.yandex.financialawareness.presentation.screens.expenses.ExpensesScreen
import quo.yandex.financialawareness.presentation.screens.income.IncomeScreen
import quo.yandex.financialawareness.presentation.screens.settings.SettingsScreen
import quo.yandex.financialawareness.presentation.screens.SplashScreen

enum class Destination(
    val route: String,
    val label: String,
    val icon: Int,
    val contentDescription: String
) {
    EXPENSES("expanses", "Расходы", R.drawable.ic_downtrend, "Расходы"),
    INCOME("income", "Доходы", R.drawable.ic_uptrend, "Доходы"),
    ACCOUNT("account", "Счёт", R.drawable.ic_calculator, "Счёт"),
    CATEGORIES("categories", "Статьи", R.drawable.ic_bar_chart_side, "Статьи"),
    SETTINGS("settings", "Настройки", R.drawable.ic_settings, "Настройки"),
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
        composable("splash") {
            SplashScreen {
                navController.navigate(Destination.EXPENSES.route) {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
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
