package quo.yandex.financialawareness.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import quo.yandex.financialawareness.ui.theme.Providers

@Composable
fun FAEmojiIcon(
    emoji: String,
    modifier: Modifier = Modifier,
    size: Dp = Providers.componentSize.iconMedium,
    backgroundColor: Color = Providers.color.secondaryContainer,
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = backgroundColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = emoji,
            fontSize = (size.value * 0.5).sp,
            textAlign = TextAlign.Center
        )
    }
}