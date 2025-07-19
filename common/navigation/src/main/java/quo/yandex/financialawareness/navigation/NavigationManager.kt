package quo.yandex.financialawareness.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface NavigationManager {
    fun buildNavGraph(
        builder: NavGraphBuilder,
        navController: NavController,
    )

    fun navigateToSettings(navController: NavController)
    fun navigateToIncome(navController: NavController)
    fun navigateToExpenses(navController: NavController)
    fun navigateToCategories(navController: NavController)
    fun navigateToAccount(navController: NavController)
    fun navigateToTransactionCreate(navController: NavController)
    fun navigateToTransactionUpdate(navController: NavController, transactionId: Int)
    fun navigateToAnalysis(navController: NavController)

    fun navigateBack(navController: NavController)
}