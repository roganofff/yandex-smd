package quo.yandex.financialawareness

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import quo.yandex.financialawareness.navigation.NavigationManager
import quo.yandex.financialawareness.navigation.ui.AppNavGraph
import quo.yandex.financialawareness.navigation.ui.BottomNavigationBar
import quo.yandex.financialawareness.navigation.ui.rememberBottomNavItems
import quo.yandex.financialawareness.ui.theme.Providers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navigationManager: NavigationManager) {
    val navController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route
    val bottomNavItems = rememberBottomNavItems(navController, navigationManager)


    Column(
        Modifier.background(Providers.color.surface)
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            AppNavGraph(
                navigationManager = navigationManager,
                navController = navController
            )
        }

        BottomNavigationBar(
            bottomNavItems = bottomNavItems,
            currentRoute = currentRoute
        )
    }
}