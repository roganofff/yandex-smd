package quo.yandex.financialawareness.expenses.impl.ui.screen.today

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import quo.yandex.financialawareness.expenses.impl.R
import quo.yandex.financialawareness.ui.component.FAFloatingActionButton
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAIconButton
import quo.yandex.financialawareness.ui.theme.Providers
import quo.yandex.financialawareness.expenses.impl.ui.screen.today.component.ExpensesTodayList
import quo.yandex.financialawareness.expenses.impl.ui.screen.today.component.ExpensesTodayShimmerList
import quo.yandex.financialawareness.expenses.impl.ui.screen.today.component.ExpensesTodayTotalItem
import quo.yandex.financialawareness.expenses.impl.ui.screen.today.component.ExpensesTodayTotalShimmerItem
import quo.yandex.financialawareness.expenses.impl.ui.screen.today.state.ExpensesTodayEvent
import quo.yandex.financialawareness.ui.component.FACenterAlignedTopAppBar
import quo.yandex.financialawareness.ui.component.FAErrorDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesTodayScreen(
    onHistoryClick: () -> Unit,
    onExpenseClick: (Int) -> Unit,
    onAddClick: () -> Unit,
    viewModel: ExpensesTodayViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.reduce(ExpensesTodayEvent.LoadExpenses)
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
            title = stringResource(R.string.expenses_today),
            actions = {
                FAIconButton(
                    onClick = onHistoryClick
                ) {
                    FAIcon(
                        painter = painterResource(R.drawable.ic_history),
                        contentDescription = stringResource(R.string.history),
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
                when {
                    state.isLoading -> {
                        ExpensesTodayTotalShimmerItem()
                        HorizontalDivider()
                        ExpensesTodayShimmerList()
                    }
                    else -> {
                        ExpensesTodayTotalItem(
                            totalSum = state.total,
                        )

                        HorizontalDivider()

                        ExpensesTodayList(
                            expenses = state.expenses,
                            onExpenseClick = onExpenseClick
                        )
                    }
                }
            }

            FAFloatingActionButton(
                onClick = onAddClick,
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
                viewModel.reduce(ExpensesTodayEvent.LoadExpenses)
            },
            onDismiss = {
                viewModel.reduce(ExpensesTodayEvent.HideErrorDialog)
            }
        )
    }
}