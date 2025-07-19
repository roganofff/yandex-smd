package quo.yandex.financialawareness.expenses.impl.ui.nav

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import quo.yandex.financialawareness.expenses.impl.ui.screen.history.ExpensesHistoryScreen
import quo.yandex.financialawareness.expenses.impl.ui.screen.today.ExpensesTodayScreen
import quo.yandex.financialawareness.navigation.FeatureNav
import quo.yandex.financialawareness.navigation.NavigationManager

object ExpensesNav: FeatureNav {
    override val navGraph = ExpensesNavGraph()
    override val name: String = "ExpensesFeature"

    override fun routes(
        builder: NavGraphBuilder,
        navController: NavController,
        navigationManager: NavigationManager,
    ) {
        builder.navigation(
            startDestination = navGraph.startDestination,
            route = navGraph.route
        ) {
            composable(route = navGraph.startDestination) {
                ExpensesTodayScreen(
                    onHistoryClick = {
                        navigateToHistory(navController)
                    },
                    onExpenseClick = { id ->
                        navigationManager.navigateToTransactionUpdate(navController, id)
                    },
                    onAddClick = { navigationManager.navigateToTransactionCreate(navController) },
                )
            }
            composable(route = navGraph.historyDestination) {
                ExpensesHistoryScreen(
                    onBackClick = {
                        navigationManager.navigateBack(navController)
                    },
                    onAnalysisClick = {
                        navigationManager.navigateToAnalysis(navController)
                    },
                    onExpenseClick = { id ->
                        navigationManager.navigateToTransactionUpdate(navController, id)
                    },
                )
            }
        }
    }

    override fun navigate(navController: NavController, args: Bundle?) {
        navController.navigate(navGraph.route)
    }

    private fun navigateToHistory(navController: NavController) {
        navController.navigate(navGraph.historyDestination)
    }
}