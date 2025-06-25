package quo.yandex.financialawareness.presentation.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import quo.yandex.financialawareness.presentation.navigation.Destination

@Composable
fun BottomBar(
    navController: NavController,
    startDestination: Destination,
) {
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

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
                        imageVector = ImageVector.vectorResource(destination.icon),
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
}