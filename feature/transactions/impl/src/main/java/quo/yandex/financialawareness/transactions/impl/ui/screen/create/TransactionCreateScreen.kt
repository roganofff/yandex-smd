package quo.yandex.financialawareness.transactions.impl.ui.screen.create

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import quo.yandex.financialawareness.transactions.api.model.TransactionType
import quo.yandex.financialawareness.transactions.impl.R
import quo.yandex.financialawareness.transactions.impl.ui.component.CategorySelectionBottomSheet
import quo.yandex.financialawareness.transactions.impl.ui.component.TransactionItem
import quo.yandex.financialawareness.transactions.impl.ui.component.TransactionShimmerItem
import quo.yandex.financialawareness.transactions.impl.ui.screen.create.state.TransactionCreateEffect
import quo.yandex.financialawareness.transactions.impl.ui.screen.create.state.TransactionCreateEvent
import quo.yandex.financialawareness.ui.component.FACenterAlignedTopAppBar
import quo.yandex.financialawareness.ui.component.MTDatePicker
import quo.yandex.financialawareness.ui.component.FAErrorDialog
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAIconButton
import quo.yandex.financialawareness.ui.component.FATimePicker
import quo.yandex.financialawareness.util.DateHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionCreateScreen(
    transactionType: TransactionType,
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
    viewModel: TransactionCreateViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val topBarTitle = when (transactionType) {
        TransactionType.EXPENSE -> stringResource(R.string.my_expenses)
        TransactionType.INCOME -> stringResource(R.string.my_income)
        TransactionType.ANY -> stringResource(R.string.my_transaction)
    }

    LaunchedEffect(Unit) {
        viewModel.reduce(TransactionCreateEvent.LoadData(transactionType))
    }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TransactionCreateEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is TransactionCreateEffect.TransactionCreated -> onSaveClick()
            }
        }
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
            title = topBarTitle,
            navigationIcon = {
                FAIconButton(
                    onClick = onCloseClick
                ) {
                    FAIcon(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = stringResource(R.string.exit),
                    )
                }
            },
            actions = {
                FAIconButton(
                    onClick = { viewModel.reduce(TransactionCreateEvent.OnDoneClicked) }
                ) {
                    FAIcon(
                        painter = painterResource(R.drawable.ic_done),
                        contentDescription = stringResource(R.string.done),
                    )
                }
            }
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {
            when {
                state.isLoading -> {
                    TransactionShimmerItem()
                }
                else -> {
                    TransactionItem(
                        transaction = state.transaction,
                        onAmountChange = { newAmount ->
                            viewModel.reduce(TransactionCreateEvent.UpdateAmount(newAmount))
                        },
                        onCommentChange = { newComment ->
                            viewModel.reduce(TransactionCreateEvent.UpdateComment(newComment))
                        },
                        onCategoryClick = {
                            viewModel.reduce(TransactionCreateEvent.ShowCategoryBottomSheet)
                        },
                        onDateClick = {
                            viewModel.reduce(TransactionCreateEvent.ShowDatePicker)
                        },
                        onTimeClick = {
                            viewModel.reduce(TransactionCreateEvent.ShowTimePicker)
                        },
                    )
                }
            }
        }
    }

    if (state.showBottomSheet) {
        CategorySelectionBottomSheet(
            categories = state.categories,
            onCategorySelected = { category ->
                viewModel.reduce(TransactionCreateEvent.SelectCategory(category))
                viewModel.reduce(TransactionCreateEvent.HideCategoryBottomSheet)
            },
            onDismiss = {
                viewModel.reduce(TransactionCreateEvent.HideCategoryBottomSheet)
            },
            modifier = Modifier.fillMaxWidth()
        )
    }

    if (state.showDatePicker) {
        MTDatePicker(
            selectedDate = DateHelper.parseDisplayDate(state.transaction.date),
            onDateSelected = { date ->
                viewModel.reduce(TransactionCreateEvent.OnDateSelected(date))
            },
            onDismiss = {
                viewModel.reduce(TransactionCreateEvent.HideDatePicker)
            },
        )
    }

    if (state.showTimePicker) {
        FATimePicker(
            selectedTime = DateHelper.parseDisplayDate(state.transaction.time),
            onTimeSelected = { time ->
                viewModel.reduce(TransactionCreateEvent.OnTimeSelected(time))
            },
            onDismiss = {
                viewModel.reduce(TransactionCreateEvent.HideTimePicker)
            },
        )
    }

    state.showErrorDialog?.let { message ->
        FAErrorDialog(
            message = message,
            confirmButtonText = stringResource(R.string.repeat),
            dismissButtonText = stringResource(R.string.exit),
            onConfirm = {
                viewModel.reduce(TransactionCreateEvent.LoadData(transactionType))
            },
            onDismiss = {
                viewModel.reduce(TransactionCreateEvent.HideErrorDialog)
            }
        )
    }
}