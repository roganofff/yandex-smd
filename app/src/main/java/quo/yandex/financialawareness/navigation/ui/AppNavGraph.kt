package quo.yandex.financialawareness.navigation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import quo.yandex.financialawareness.account.impl.ui.nav.AccountNav
import quo.yandex.financialawareness.navigation.NavigationManager

@Composable
fun AppNavGraph(
    navigationManager: NavigationManager,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AccountNav.navGraph.route,
        modifier = modifier
    ) {
        navigationManager.buildNavGraph(this, navController)
    }
}