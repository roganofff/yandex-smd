package quo.yandex.financialawareness.expenses.impl.ui.screen.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import quo.yandex.financialawareness.expenses.impl.R
import quo.yandex.financialawareness.expenses.impl.ui.screen.history.component.ExpensesHistoryList
import quo.yandex.financialawareness.expenses.impl.ui.screen.history.component.ExpensesHistoryShimmerList
import quo.yandex.financialawareness.expenses.impl.ui.screen.history.component.ExpensesHistoryTotalItem
import quo.yandex.financialawareness.expenses.impl.ui.screen.history.component.ExpensesHistoryTotalShimmerItem
import quo.yandex.financialawareness.expenses.impl.ui.screen.history.state.ExpensesHistoryEvent
import quo.yandex.financialawareness.ui.component.FACenterAlignedTopAppBar
import quo.yandex.financialawareness.ui.component.FADatePicker
import quo.yandex.financialawareness.ui.component.FAErrorDialog
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAIconButton
import quo.yandex.financialawareness.ui.component.FAPeriodItem
import quo.yandex.financialawareness.util.DateHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesHistoryScreen(
    onBackClick: () -> Unit,
    onAnalysisClick: () -> Unit,
    onExpenseClick: (Int) -> Unit,
    viewModel: ExpensesHistoryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.reduce(ExpensesHistoryEvent.LoadExpenses)
    }

    DisposableEffect(viewModel) {
        onDispose {
            viewModel.cancelAllTasks()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        FACenterAlignedTopAppBar(
            title = stringResource(R.string.my_history),
            navigationIcon = {
                FAIconButton(
                    onClick = onBackClick
                ) {
                    FAIcon(
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = stringResource(R.string.back),
                    )
                }
            },
            actions = {
                FAIconButton(
                    onClick = onAnalysisClick
                ) {
                    FAIcon(
                        painter = painterResource(R.drawable.ic_analysis),
                        contentDescription = stringResource(R.string.analysis),
                    )
                }
            }
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                FAPeriodItem(
                    startDate = state.startDate,
                    endDate = state.endDate,
                    onStartDateClick = {
                        viewModel.reduce(ExpensesHistoryEvent.ShowStartDatePicker)
                    },
                    onEndDateClick = {
                        viewModel.reduce(ExpensesHistoryEvent.ShowEndDatePicker)
                    },
                )

                HorizontalDivider()

                when {
                    state.isLoading -> {
                        ExpensesHistoryTotalShimmerItem()
                        HorizontalDivider()
                        ExpensesHistoryShimmerList()
                    }
                    else -> {
                        ExpensesHistoryTotalItem(
                            totalSum = state.total,
                        )

                        HorizontalDivider()

                        ExpensesHistoryList(
                            expenses = state.expenses,
                            onExpenseClick = onExpenseClick
                        )
                    }
                }
            }
        }
    }

    if (state.showStartDatePicker) {
        FADatePicker(
            selectedDate = DateHelper.parseDisplayDate(state.startDate),
            onDateSelected = { date ->
                viewModel.reduce(ExpensesHistoryEvent.OnStartDateSelected(date))
            },
            onDismiss = {
                viewModel.reduce(ExpensesHistoryEvent.HideDatePicker)
            },
            maxDate = DateHelper.parseDisplayDate(state.endDate)
        )
    }

    if (state.showEndDatePicker) {
        FADatePicker(
            selectedDate = DateHelper.parseDisplayDate(state.endDate),
            onDateSelected = { date ->
                viewModel.reduce(ExpensesHistoryEvent.OnEndDateSelected(date))
            },
            onDismiss = {
                viewModel.reduce(ExpensesHistoryEvent.HideDatePicker)
            },
            minDate = DateHelper.parseDisplayDate(state.startDate)
        )
    }

    state.showErrorDialog?.let { message ->
        FAErrorDialog(
            message = message,
            confirmButtonText = stringResource(R.string.repeat),
            dismissButtonText = stringResource(R.string.exit),
            onConfirm = {
                viewModel.reduce(ExpensesHistoryEvent.LoadExpenses)
            },
            onDismiss = {
                viewModel.reduce(ExpensesHistoryEvent.HideErrorDialog)
            }
        )
    }
}