package quo.yandex.financialawareness.presentation.screens.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.screens.account.state.AccountEvent
import quo.yandex.financialawareness.presentation.screens.account.viewmodel.AccountViewModel
import quo.yandex.financialawareness.presentation.ui.components.FAFloatingButton
import quo.yandex.financialawareness.presentation.ui.components.FAErrorDialog


@Preview
@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    onUpdateClick: () -> Unit = {},
    viewModel: AccountViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.reduce(AccountEvent.LoadAccount)
    }

    DisposableEffect(viewModel) {
        onDispose {
            viewModel.cancelAllTasks()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        when {
            state.isLoading -> {
                AccountShimmerItem()
            }
            else -> {
                AccountItem(state.account)
            }
        }
//        val items = provideAccountMockData()
//        itemsIndexed(items) { index, item ->
//            FAListItem(
//                title = item.title,
//                comment = item.comment,
//                trailTitle = item.price,
//                leadIcon = item.leadIcon,
//                trailIcon = item.trailIcon,
//                isLeading = item.isLeading,
//                isEmojiIcon = item.isEmojiIcon,
//            )
//            if (index < items.lastIndex) {
//                FADivider()
//            }
//        }
    }

    FAFloatingButton(modifier)
    state.showErrorDialog?.let { message ->
        FAErrorDialog(
            message = message,
            confirmButtonText = stringResource(R.string.repeat),
            dismissButtonText = stringResource(R.string.exit),
            onConfirm = {
                viewModel.reduce(AccountEvent.LoadAccount)
            },
            onDismiss = {
                viewModel.reduce(AccountEvent.HideErrorDialog)
            }
        )
    }
}
