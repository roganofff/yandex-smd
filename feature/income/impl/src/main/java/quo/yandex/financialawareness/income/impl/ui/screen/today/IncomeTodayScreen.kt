package quo.yandex.financialawareness.income.impl.ui.screen.today

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
import quo.yandex.financialawareness.ui.component.FAFloatingActionButton
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAIconButton
import quo.yandex.financialawareness.ui.theme.Providers
import quo.yandex.financialawareness.income.impl.R
import quo.yandex.financialawareness.income.impl.ui.screen.today.component.IncomeTodayList
import quo.yandex.financialawareness.income.impl.ui.screen.today.component.IncomeTodayShimmerList
import quo.yandex.financialawareness.income.impl.ui.screen.today.component.IncomeTodayTotalItem
import quo.yandex.financialawareness.income.impl.ui.screen.today.component.IncomeTodayTotalShimmerItem
import quo.yandex.financialawareness.income.impl.ui.screen.today.state.IncomeTodayEvent
import quo.yandex.financialawareness.ui.component.FACenterAlignedTopAppBar
import quo.yandex.financialawareness.ui.component.FAErrorDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeTodayScreen(
    onHistoryClick: () -> Unit,
    onIncomeClick: (Int) -> Unit,
    onAddClick: () -> Unit,
    viewModel: IncomeTodayViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.reduce(IncomeTodayEvent.LoadIncome)
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
            title = stringResource(R.string.income_today),
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
                        IncomeTodayTotalShimmerItem()
                        HorizontalDivider()
                        IncomeTodayShimmerList()
                    }
                    else -> {
                        IncomeTodayTotalItem(
                            totalSum = state.total,
                        )

                        HorizontalDivider()

                        IncomeTodayList(
                            expenses = state.income,
                            onIncomeClick = onIncomeClick
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
                viewModel.reduce(IncomeTodayEvent.LoadIncome)
            },
            onDismiss = {
                viewModel.reduce(IncomeTodayEvent.HideErrorDialog)
            }
        )
    }
}