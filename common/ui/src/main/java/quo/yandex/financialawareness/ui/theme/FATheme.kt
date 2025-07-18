package quo.yandex.financialawareness.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import quo.yandex.financialawareness.ui.theme.designsystem.ComponentSize
import quo.yandex.financialawareness.ui.theme.designsystem.DarkColorScheme
import quo.yandex.financialawareness.ui.theme.designsystem.LightColorScheme
import quo.yandex.financialawareness.ui.theme.designsystem.LocalColorScheme
import quo.yandex.financialawareness.ui.theme.designsystem.LocalComponentSizes
import quo.yandex.financialawareness.ui.theme.designsystem.LocalShape
import quo.yandex.financialawareness.ui.theme.designsystem.LocalSpacing
import quo.yandex.financialawareness.ui.theme.designsystem.LocalTypography
import quo.yandex.financialawareness.ui.theme.designsystem.Shape
import quo.yandex.financialawareness.ui.theme.designsystem.Spacing
import quo.yandex.financialawareness.ui.theme.designsystem.Typography

@Composable
fun FATheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalColorScheme provides colors,
        LocalTypography provides Typography(),
        LocalSpacing provides Spacing(),
        LocalShape provides Shape(),
        LocalComponentSizes provides ComponentSize()
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = MaterialTheme.typography,
            content = content
        )
    }
}