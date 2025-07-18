package quo.yandex.financialawareness.account.impl.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import quo.yandex.financialawareness.account.impl.R
import quo.yandex.financialawareness.account.impl.ui.main.component.AccountItem
import quo.yandex.financialawareness.account.impl.ui.main.component.AccountShimmerItem
import quo.yandex.financialawareness.account.impl.ui.main.state.AccountEvent
import quo.yandex.financialawareness.ui.component.FACenterAlignedTopAppBar
import quo.yandex.financialawareness.ui.component.FAErrorDialog
import quo.yandex.financialawareness.ui.component.FAFloatingActionButton
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAIconButton
import quo.yandex.financialawareness.ui.theme.Providers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    onUpdateClick: () -> Unit,
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
        modifier = Modifier.fillMaxSize(),
    ) {
        FACenterAlignedTopAppBar(
            title = stringResource(R.string.my_account),
            actions = {
                FAIconButton(
                    onClick = onUpdateClick
                ) {
                    FAIcon(
                        painter = painterResource(R.drawable.ic_edit),
                        contentDescription = stringResource(R.string.edit),
                    )
                }
            }
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {
            when {
                state.isLoading -> {
                    AccountShimmerItem(
                    )
                }
                else -> {
                    AccountItem(
                        account = state.account,
                    )
                }
            }

            FAFloatingActionButton(
                onClick = { },
                contentDescription = stringResource(R.string.add),
                painter = painterResource(R.drawable.ic_add),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(Providers.spacing.m)
            )
        }
    }
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