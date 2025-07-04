package quo.yandex.financialawareness.presentation.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import quo.yandex.financialawareness.presentation.navigation.Destination

@Composable
fun FABottomBar(
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
                label = { Text(destination.label, style = if (selectedDestination == index) {
                    typography.labelMedium
                } else {
                    typography.labelSmall
                }) },
                colors = NavigationBarItemColors(
                    selectedIconColor = colorScheme.primaryContainer,
                    selectedTextColor = colorScheme.onSurface,
                    selectedIndicatorColor = colorScheme.secondaryContainer,
                    unselectedIconColor = colorScheme.onSurfaceVariant,
                    unselectedTextColor = colorScheme.onSurfaceVariant,
                    disabledIconColor = colorScheme.onSurfaceVariant,
                    disabledTextColor = colorScheme.onSurfaceVariant,
                )
            )
        }
    }
}