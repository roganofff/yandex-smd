package quo.yandex.financialawareness.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import quo.yandex.financialawareness.ui.theme.Providers

@Composable
fun FAText(
    text: String,
    style: TextStyle = Providers.typography.bodyL,
    color: Color = Providers.color.onSurface,
    maxLines: Int = Int.MAX_VALUE,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = Providers.spacing.none,
        vertical = Providers.spacing.none
    )
) {
    Text(
        text = text,
        style = style,
        modifier = modifier.padding(contentPadding),
        color = color,
        maxLines = maxLines
    )
}