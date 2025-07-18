package quo.yandex.financialawareness.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import quo.yandex.financialawareness.ui.theme.Providers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FACenterAlignedTopAppBar(
    title: String,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = Providers.color.primary,
    foregroundColor: Color = Providers.color.secondary,
) {

    CenterAlignedTopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor,
            titleContentColor = foregroundColor,
            actionIconContentColor = foregroundColor,
            navigationIconContentColor = foregroundColor
        ),
        navigationIcon = { navigationIcon?.invoke() },
        actions = actions,
    )
}