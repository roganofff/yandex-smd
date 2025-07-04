package quo.yandex.financialawareness.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import quo.yandex.financialawareness.R

@Preview
@Composable
fun FAFloatingButton(modifier: Modifier = Modifier) {
    val insets = WindowInsets.navigationBars.asPaddingValues()
    val bottomInset = insets.calculateBottomPadding()

    Box(
        modifier = modifier
            .padding(bottom = bottomInset)
            .fillMaxSize()
            .padding(end = 16.dp, bottom = 14.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            modifier = Modifier.size(56.dp),
            onClick = { },
            shape = CircleShape,
            containerColor = colorScheme.primaryContainer,
            contentColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            ),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                contentDescription = null,
                Modifier.size(16.dp)
            )
        }
    }
}