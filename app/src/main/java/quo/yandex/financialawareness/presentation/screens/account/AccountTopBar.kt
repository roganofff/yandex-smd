package quo.yandex.financialawareness.presentation.screens.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.ui.components.FATopBar

@Composable
fun AccountTopBar() = FATopBar(
    title = stringResource(R.string.my_account),
) {
    IconButton(
        onClick = { },
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
            contentDescription = stringResource(R.string.edit),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(24.dp)
        )
    }
}