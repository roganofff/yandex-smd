package quo.yandex.financialawareness.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import quo.yandex.financialawareness.ui.theme.Providers

@Composable
fun FAIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = Providers.componentSize.buttonMedium,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(size),
        enabled = enabled
    ) {
        content()
    }
}