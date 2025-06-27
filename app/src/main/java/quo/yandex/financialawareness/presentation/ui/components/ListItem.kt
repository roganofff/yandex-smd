package quo.yandex.financialawareness.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    title: String,
    comment: String? = null,
    price: String? = null,
    leadIcon: String? = null,
    trailIcon: Int? = null,
    isLeading: Boolean = false,
    isEmojiIcon: Boolean = true,
) {
    val greenBackground = Color(0xFFD4FAE5)
    val whiteBackground = Color(0xFFFEF7FF)

    val rowBackground = if (isLeading) greenBackground else Color.Transparent
    val circleBackground = if (isLeading) whiteBackground else greenBackground

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(rowBackground)
            .height(if (isLeading) 56.dp else 68.dp),
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
                Text(
                    text = leadIcon,
                    fontSize = if (isEmojiIcon) 16.sp else 10.sp,
                    color = colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
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

        price?.let {
            Text(
                text = price,
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