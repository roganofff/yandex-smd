package quo.yandex.financialawareness.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.navigation.AppNavHost
import quo.yandex.financialawareness.presentation.navigation.Destination
import quo.yandex.financialawareness.presentation.screens.account.AccountTopBar
import quo.yandex.financialawareness.presentation.screens.account.state.AccountUpdateEvent
import quo.yandex.financialawareness.presentation.screens.account.update.AccountUpdateScreen
import quo.yandex.financialawareness.presentation.screens.account.update.AccountUpdateTopBar
import quo.yandex.financialawareness.presentation.screens.account.update.viewmodel.AccountUpdateViewModel
import quo.yandex.financialawareness.presentation.screens.categories.CategoriesTopBar
import quo.yandex.financialawareness.presentation.screens.expenses.ExpensesTopBar
import quo.yandex.financialawareness.presentation.screens.income.IncomeTopBar
import quo.yandex.financialawareness.presentation.screens.settings.SettingsTopBar
import quo.yandex.financialawareness.presentation.ui.components.FABottomBar
import quo.yandex.financialawareness.presentation.ui.theme.FinancialAwarenessTheme

@Preview
@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val startDestination = Destination.ACCOUNT
    var showSplash by rememberSaveable { mutableStateOf(true) }

    FinancialAwarenessTheme {
        if (showSplash) {
            SplashScreen { showSplash = false }
        } else {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    when (currentRoute) {
                        Destination.EXPENSES.route -> ExpensesTopBar()
                        Destination.INCOME.route -> IncomeTopBar()
                        Destination.ACCOUNT.route -> AccountTopBar(
                            onUpdateClick = {
                                navController.navigate("account_update")
                            }
                        )

                        Destination.CATEGORIES.route -> CategoriesTopBar()
                        Destination.SETTINGS.route -> SettingsTopBar()
                        "account_update" -> {
                            AccountUpdateTopBar(
                                onCloseClick = {
                                    if (navController.previousBackStackEntry != null) {
                                        navController.popBackStack()
                                    }
                                },
                            )
                        }
                    }
                },
                bottomBar = {
                    FABottomBar(
                        navController = navController,
                        startDestination = startDestination
                    )
                },
            ) { innerPadding ->
                val insets = WindowInsets.navigationBars.asPaddingValues()
                val bottomInset = insets.calculateBottomPadding()

                Box(
                    modifier = Modifier
                        .padding(
                            top = innerPadding.calculateTopPadding(),
                            bottom = innerPadding.calculateBottomPadding() - bottomInset
                        )
                ) {
                    AppNavHost(
                        navController, startDestination, Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}