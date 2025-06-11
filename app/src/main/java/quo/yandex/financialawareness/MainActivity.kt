package quo.yandex.financialawareness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import quo.yandex.financialawareness.presentation.screens.AccountScreen
import quo.yandex.financialawareness.presentation.screens.CategoriesScreen
import quo.yandex.financialawareness.presentation.screens.ExpensesScreen
import quo.yandex.financialawareness.presentation.screens.IncomeScreen
import quo.yandex.financialawareness.presentation.screens.SettingsScreen
import quo.yandex.financialawareness.presentation.ui.theme.FinancialAwarenessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppScreen()
        }
    }
}

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

@Preview
@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.EXPENSES
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    FinancialAwarenessTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                    Destination.entries.forEachIndexed { index, destination ->
                        NavigationBarItem(
                            selected = selectedDestination == index,
                            onClick = {
                                navController.navigate(route = destination.route)
                                selectedDestination = index
                            },
                            icon = {
                                Image(
                                    painterResource(destination.icon),
                                    contentDescription = destination.contentDescription,
                                    contentScale = ContentScale.Crop
                                )
                            },
                            label = { Text(destination.label) }
                        )
                    }
                }
            },
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AppNavHost(navController, startDestination)
            }
        }
    }
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