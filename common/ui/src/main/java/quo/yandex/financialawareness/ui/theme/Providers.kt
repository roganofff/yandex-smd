package quo.yandex.financialawareness.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import quo.yandex.financialawareness.ui.theme.designsystem.ComponentSize
import quo.yandex.financialawareness.ui.theme.designsystem.LocalColorScheme
import quo.yandex.financialawareness.ui.theme.designsystem.LocalComponentSizes
import quo.yandex.financialawareness.ui.theme.designsystem.LocalShape
import quo.yandex.financialawareness.ui.theme.designsystem.LocalSpacing
import quo.yandex.financialawareness.ui.theme.designsystem.LocalTypography
import quo.yandex.financialawareness.ui.theme.designsystem.Shape
import quo.yandex.financialawareness.ui.theme.designsystem.Spacing
import quo.yandex.financialawareness.ui.theme.designsystem.Typography

object Providers {
    val spacing: Spacing
        @Composable
        get() = LocalSpacing.current

    val shape: Shape
        @Composable
        get() = LocalShape.current

    val typography: Typography
        @Composable
        get() = LocalTypography.current

    val color: ColorScheme
        @Composable
        get() = LocalColorScheme.current

    val componentSize: ComponentSize
        @Composable
        get() = LocalComponentSizes.current
}