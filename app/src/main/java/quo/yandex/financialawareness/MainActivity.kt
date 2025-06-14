package quo.yandex.financialawareness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import quo.yandex.financialawareness.presentation.navigation.AppNavHost
import quo.yandex.financialawareness.presentation.navigation.Destination
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
                                Icon(
                                    painter = painterResource(destination.icon),
                                    contentDescription = destination.contentDescription,
                                )
                            },
                            label = { Text(destination.label) },
                            colors = NavigationBarItemColors(
                                selectedIconColor = Color(0xFF2AE881),
                                selectedTextColor = Color(0xFF1D1B20),
                                selectedIndicatorColor = Color(0xFFD4FAE6),
                                unselectedIconColor = Color(0xFF49454F),
                                unselectedTextColor = Color(0xFF49454F),
                                disabledIconColor = Color(0xFF49454F),
                                disabledTextColor = Color(0xFF49454F),
                            )
                        )
                    }
                }
            },
        ) { innerPadding ->
            AppNavHost(navController, startDestination, Modifier.padding(innerPadding))
            println(innerPadding)
        }
    }
}