package quo.yandex.financialawareness.presentation.screens.account.update

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.screens.account.state.AccountUpdateEvent
import quo.yandex.financialawareness.presentation.screens.account.update.viewmodel.AccountUpdateViewModel
import quo.yandex.financialawareness.presentation.ui.components.FATopBar

@Composable
fun AccountUpdateTopBar(
    onCloseClick: () -> Unit,
    viewModel: AccountUpdateViewModel = hiltViewModel()
) = FATopBar(
    title = stringResource(R.string.my_account),
    navigationIcon = {
        IconButton(
            onClick = onCloseClick
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_close),
                contentDescription = stringResource(R.string.exit),
            )
        }
    },
    actions = {
        IconButton(
            onClick = {
                viewModel.reduce(AccountUpdateEvent.OnDoneClicked)
            }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_done),
                contentDescription = null,
            )
        }
    }
)
