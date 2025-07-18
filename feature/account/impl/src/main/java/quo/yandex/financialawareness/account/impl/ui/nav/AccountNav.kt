package quo.yandex.financialawareness.account.impl.ui.nav

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import quo.yandex.financialawareness.account.impl.ui.main.AccountScreen
import quo.yandex.financialawareness.account.impl.ui.update.AccountUpdateScreen
import quo.yandex.financialawareness.navigation.FeatureNav
import quo.yandex.financialawareness.navigation.NavigationManager

object AccountNav: FeatureNav {
    override val navGraph = AccountNavGraph()

    override val name: String = "AccountFeature"

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
                AccountScreen(
                    onUpdateClick = {
                        navigateToAccountUpdate(navController)
                    })
            }
            composable(route = navGraph.updateDestination) {
                AccountUpdateScreen(
                    onCloseClick = {
                        navigationManager.navigateBack(navController)
                    },
                    onSaveClick = {
                        navigate(navController)
                    }
                )
            }
        }
    }

    override fun navigate(navController: NavController, args: Bundle?) {
        navController.navigate(navGraph.route)
    }

    private fun navigateToAccountUpdate(navController: NavController) {
        navController.navigate(navGraph.updateDestination)
    }
}