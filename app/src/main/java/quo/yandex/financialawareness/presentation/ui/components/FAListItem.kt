package quo.yandex.financialawareness.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FAListItem(
    modifier: Modifier = Modifier,
    title: String,
    comment: String? = null,
    trailTitle: String? = null,
    leadIcon: Any? = null,
    trailIcon: Int? = null,
    height: Dp = 72.dp,
    mainColor: Color = Color(0xFFD4FAE5),
    backgroundColor: Color = Color(0xFFFEF7FF),
    isLeading: Boolean = false,
    isEmojiIcon: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    val rowBackground = if (isLeading) mainColor else Color.Transparent
    val circleBackground = if (isLeading) backgroundColor else mainColor
    val interactionSource = remember { MutableInteractionSource() }
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    } else {
        Modifier
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(rowBackground)
            .height(height)
//            .height(if (isLeading) 56.dp else 68.dp)
            .then(clickableModifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadIcon?.let {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(24.dp)
                    .background(color = circleBackground, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                when (leadIcon) {
                    is String -> {
                        Text(
                            text = leadIcon,
                            fontSize = if (isEmojiIcon) 16.sp else 10.sp,
                            color = colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                    }
                    is Int -> {
                        Icon(
                            imageVector = ImageVector.vectorResource(leadIcon),
                            tint = colorScheme.onError,
                            contentDescription = null,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = typography.bodyLarge
            )
            comment?.let {
                Text(
                    text = comment,
                    style = typography.bodyMedium
                )
            }
        }

        trailTitle?.let {
            Text(
                text = trailTitle,
                style = typography.bodyLarge,
                modifier = Modifier.padding(end = 16.dp, start = 16.dp)
            )
        }

        trailIcon?.let {
            Image(
                imageVector = ImageVector.vectorResource(trailIcon),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
//                    .size(24.dp)  only for now
            )
        }
    }
}