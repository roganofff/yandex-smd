package quo.yandex.financialawareness.settings.impl.ui.nav

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import quo.yandex.financialawareness.navigation.FeatureNav
import quo.yandex.financialawareness.navigation.NavGraph
import quo.yandex.financialawareness.navigation.NavigationManager
import quo.yandex.financialawareness.settings.impl.ui.screen.SettingsScreen

object SettingsNav: FeatureNav {
    override val navGraph = object : NavGraph() {
        override val route = "settings"
        override val startDestination = "settings_main_screen"
    }
    override val name: String = "SettingsFeature"

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
                SettingsScreen()
            }
        }
    }

    override fun navigate(navController: NavController, args: Bundle?) {
        navController.navigate(navGraph.route)
    }
}