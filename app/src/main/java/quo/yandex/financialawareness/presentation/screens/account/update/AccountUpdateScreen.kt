package quo.yandex.financialawareness.presentation.screens.account.update

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.screens.account.state.AccountUpdateEffect
import quo.yandex.financialawareness.presentation.screens.account.state.AccountUpdateEvent
import quo.yandex.financialawareness.presentation.screens.account.update.viewmodel.AccountUpdateViewModel
import quo.yandex.financialawareness.presentation.ui.components.FAErrorDialog
import quo.yandex.financialawareness.presentation.screens.account.component.AccountUpdateItem
import quo.yandex.financialawareness.presentation.screens.account.component.AccountUpdateShimmerItem
import quo.yandex.financialawareness.presentation.screens.account.component.CurrencySelectionBottomSheet

@Composable
fun AccountUpdateScreen(
    modifier: Modifier = Modifier,
    onSaveClick: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(),
    viewModel: AccountUpdateViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.reduce(AccountUpdateEvent.LoadData)
    }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is AccountUpdateEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is AccountUpdateEffect.AccountUpdated -> onSaveClick()
            }
        }
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
        Box(
            modifier = Modifier.weight(1f)
        ) {
            when {
                state.isLoading -> {
                    AccountUpdateShimmerItem()
                }
                else -> {
                    AccountUpdateItem(
                        account = state.account,
                        onNameChange = { newName ->
                            viewModel.reduce(AccountUpdateEvent.UpdateName(newName))
                        },
                        onBalanceChange = { newBalance ->
                            viewModel.reduce(AccountUpdateEvent.UpdateBalance(newBalance))
                        },
                        onCurrencyClick = {
                            viewModel.reduce(AccountUpdateEvent.ShowCurrencyBottomSheet)
                        }
                    )
                }
            }
        }
    }

    if (state.showBottomSheet) {
        CurrencySelectionBottomSheet(
            currencies = state.currencies,
            onCurrencySelected = { currency ->
                viewModel.reduce(AccountUpdateEvent.SelectCurrency(currency))
                viewModel.reduce(AccountUpdateEvent.HideCurrencyBottomSheet)
            },
            onDismiss = {
                viewModel.reduce(AccountUpdateEvent.HideCurrencyBottomSheet)
            },
            modifier = Modifier.fillMaxWidth()
        )
    }

    state.showErrorDialog?.let { message ->
        FAErrorDialog(
            message = message,
            confirmButtonText = stringResource(R.string.repeat),
            dismissButtonText = stringResource(R.string.exit),
            onConfirm = {
                viewModel.reduce(AccountUpdateEvent.LoadData)
            },
            onDismiss = {
                viewModel.reduce(AccountUpdateEvent.HideErrorDialog)
            }
        )
    }
}