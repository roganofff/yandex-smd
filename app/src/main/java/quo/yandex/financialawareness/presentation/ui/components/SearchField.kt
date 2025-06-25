package quo.yandex.financialawareness.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quo.yandex.financialawareness.R

@Composable
fun SearchField(
    query: String,
) {
    BasicTextField(
        value = query,
        onValueChange = { },
        singleLine = true,
        textStyle = TextStyle(
            color = Color(0xFF49454F),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal, // 400
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        cursorBrush = SolidColor(Color(0xFF49454F)),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFECE6F0))
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (query.isEmpty()) {
                        Text(
                            text = "Найти статью",
                            style = TextStyle(
                                color = Color(0xFF49454F),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 24.sp,
                                letterSpacing = 0.5.sp
                            )
                        )
                    }
                    innerTextField()
                }
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                    contentDescription = "Поиск",
                    tint = Color(0xFF49454F),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { }
                )
            }
        }
    )
}