package quo.yandex.financialawareness.transactions.impl.ui.screen.update

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import quo.yandex.financialawareness.transactions.impl.ui.screen.update.state.TransactionUpdateEffect
import quo.yandex.financialawareness.transactions.impl.ui.screen.update.state.TransactionUpdateEvent
import quo.yandex.financialawareness.ui.component.FAButton
import quo.yandex.financialawareness.ui.component.FACenterAlignedTopAppBar
import quo.yandex.financialawareness.ui.component.MTDatePicker
import quo.yandex.financialawareness.ui.component.FAErrorDialog
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAIconButton
import quo.yandex.financialawareness.ui.component.FATimePicker
import quo.yandex.financialawareness.ui.theme.Providers
import quo.yandex.financialawareness.util.DateHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionUpdateScreen(
    transactionId: Int,
    transactionType: TransactionType,
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
    viewModel: TransactionUpdateViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val topBarTitle = when (transactionType) {
        TransactionType.EXPENSE -> stringResource(R.string.my_expenses)
        TransactionType.INCOME -> stringResource(R.string.my_income)
        TransactionType.ANY -> stringResource(R.string.my_transaction)
    }

    val deleteButtonText = when (transactionType) {
        TransactionType.EXPENSE -> stringResource(R.string.delete_expense)
        TransactionType.INCOME -> stringResource(R.string.delete_income)
        TransactionType.ANY -> stringResource(R.string.delete_transaction)
    }

    LaunchedEffect(Unit) {
        viewModel.reduce(TransactionUpdateEvent.LoadData(transactionId, transactionType))
    }

    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TransactionUpdateEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is TransactionUpdateEffect.TransactionUpdated -> onSaveClick()
                is TransactionUpdateEffect.TransactionDeleted -> onSaveClick()
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
                    onClick = { viewModel.reduce(TransactionUpdateEvent.OnDoneClicked) }
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
            Column {
                when {
                    state.isLoading -> {
                        TransactionShimmerItem()
                    }
                    else -> {
                        TransactionItem(
                            transaction = state.transaction,
                            onAmountChange = { newAmount ->
                                viewModel.reduce(TransactionUpdateEvent.UpdateAmount(newAmount))
                            },
                            onCommentChange = { newComment ->
                                viewModel.reduce(TransactionUpdateEvent.UpdateComment(newComment))
                            },
                            onCategoryClick = {
                                viewModel.reduce(TransactionUpdateEvent.ShowCategoryBottomSheet)
                            },
                            onDateClick = {
                                viewModel.reduce(TransactionUpdateEvent.ShowDatePicker)
                            },
                            onTimeClick = {
                                viewModel.reduce(TransactionUpdateEvent.ShowTimePicker)
                            },
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Providers.spacing.xl))

                FAButton(
                    onClick = {
                        viewModel.reduce(TransactionUpdateEvent.OnDeleteClicked(transactionId))
                    },
                    containerColor = Providers.color.error,
                    contentColor = Providers.color.onError,
                    text = deleteButtonText,
                    modifier = Modifier.padding(horizontal = Providers.spacing.m)
                )
            }
        }
    }

    if (state.showBottomSheet) {
        CategorySelectionBottomSheet(
            categories = state.categories,
            onCategorySelected = { category ->
                viewModel.reduce(TransactionUpdateEvent.SelectCategory(category))
                viewModel.reduce(TransactionUpdateEvent.HideCategoryBottomSheet)
            },
            onDismiss = {
                viewModel.reduce(TransactionUpdateEvent.HideCategoryBottomSheet)
            },
            modifier = Modifier.fillMaxWidth()
        )
    }

    if (state.showDatePicker) {
        MTDatePicker(
            selectedDate = DateHelper.parseDisplayDate(state.transaction.date),
            onDateSelected = { date ->
                viewModel.reduce(TransactionUpdateEvent.OnDateSelected(date))
            },
            onDismiss = {
                viewModel.reduce(TransactionUpdateEvent.HideDatePicker)
            },
        )
    }

    if (state.showTimePicker) {
        FATimePicker(
            selectedTime = DateHelper.parseDisplayDate(state.transaction.time),
            onTimeSelected = { time ->
                viewModel.reduce(TransactionUpdateEvent.OnTimeSelected(time))
            },
            onDismiss = {
                viewModel.reduce(TransactionUpdateEvent.HideTimePicker)
            },
        )
    }

    state.showErrorDialog?.let { message ->
        FAErrorDialog(
            message = message,
            confirmButtonText = stringResource(R.string.repeat),
            dismissButtonText = stringResource(R.string.exit),
            onConfirm = {
                viewModel.reduce(TransactionUpdateEvent.LoadData(transactionId, transactionType))
            },
            onDismiss = {
                viewModel.reduce(TransactionUpdateEvent.HideErrorDialog)
            }
        )
    }
}