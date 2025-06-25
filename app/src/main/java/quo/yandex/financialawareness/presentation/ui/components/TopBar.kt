package quo.yandex.financialawareness.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quo.yandex.financialawareness.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    actions: @Composable (RowScope.() -> Unit) = {}
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF2AE881),
            titleContentColor = Color(0xFF1D1B20),
        ),
        title = {
            Text(
                title,
                maxLines = 1,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp,
                fontWeight = FontWeight(400),
            )
        },
        actions = actions,
    )
}
