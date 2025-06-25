package quo.yandex.financialawareness.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.navigation.AppNavHost
import quo.yandex.financialawareness.presentation.navigation.Destination
import quo.yandex.financialawareness.presentation.ui.components.BottomBar
import quo.yandex.financialawareness.presentation.ui.components.TopBar
import quo.yandex.financialawareness.presentation.ui.theme.FinancialAwarenessTheme

@Preview
@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val startDestination = Destination.EXPENSES
    var showSplash by rememberSaveable { mutableStateOf(true) }

    FinancialAwarenessTheme {
        if (showSplash) {
            SplashScreen { showSplash = false }
        } else {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    when (currentRoute) {
                        Destination.EXPENSES.route -> TopBar(
                            title = "Расходы сегодня",
                            actions = {
                                IconButton(
                                    onClick = { },
                                    content = {
                                        Image(
                                            imageVector = ImageVector.vectorResource(R.drawable.ic_history),
                                            contentDescription = "История",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                )
                            }
                        )

                        Destination.INCOME.route -> TopBar(
                            title = "Доходы сегодня",
                            actions = {
                                IconButton(
                                    onClick = { },
                                    content = {
                                        Image(
                                            imageVector = ImageVector.vectorResource(R.drawable.ic_history),
                                            contentDescription = "История",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                )
                            }
                        )

                        Destination.ACCOUNT.route -> TopBar(
                            title = "Мой счёт",
                            actions = {
                                IconButton(
                                    onClick = { },
                                    content = {
                                        Image(
                                            imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                                            contentDescription = "Редактировать",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                )
                            }
                        )

                        Destination.CATEGORIES.route -> TopBar(title = "Мои статьи")
                        Destination.SETTINGS.route -> TopBar(title = "Настройки")
                    }
                },
                bottomBar = {
                    BottomBar(
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