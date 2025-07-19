package quo.yandex.financialawareness.analysis.impl.ui.screen

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
import quo.yandex.financialawareness.analysis.impl.ui.screen.component.AnalysisList
import quo.yandex.financialawareness.analysis.impl.ui.screen.component.AnalysisShimmerList
import quo.yandex.financialawareness.analysis.impl.ui.screen.component.AnalysisTotalItem
import quo.yandex.financialawareness.analysis.impl.ui.screen.component.AnalysisTotalShimmerItem
import quo.yandex.financialawareness.analysis.impl.ui.screen.state.AnalysisEvent
import quo.yandex.financialawareness.transactions.api.model.TransactionType
import quo.yandex.financialawareness.ui.R
import quo.yandex.financialawareness.ui.component.FACenterAlignedTopAppBar
import quo.yandex.financialawareness.ui.component.FADatePicker
import quo.yandex.financialawareness.ui.component.FAErrorDialog
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAIconButton
import quo.yandex.financialawareness.ui.component.FAPeriodItem
import quo.yandex.financialawareness.ui.theme.Providers
import quo.yandex.financialawareness.util.DateHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalysisScreen(
    transactionType: TransactionType,
    onBackClick: () -> Unit,
    onCategoryClick: (Int) -> Unit,
    viewModel: AnalysisViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.reduce(AnalysisEvent.LoadAnalysis(transactionType))
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
            title = stringResource(R.string.analysis),
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
            backgroundColor = Providers.color.surface,
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
                        viewModel.reduce(AnalysisEvent.ShowStartDatePicker)
                    },
                    onEndDateClick = {
                        viewModel.reduce(AnalysisEvent.ShowEndDatePicker)
                    },
                    containerColor = Providers.color.surface,
                    dateHasAccent = true,
                    accentColor = Providers.color.primary
                )

                HorizontalDivider()

                when {
                    state.isLoading -> {
                        AnalysisTotalShimmerItem()
                        HorizontalDivider()
                        AnalysisShimmerList()
                    }
                    else -> {
                        AnalysisTotalItem(
                            totalSum = state.total,
                        )

                        HorizontalDivider()

                        AnalysisList(
                            categories = state.categories,
                            onCategoryClick = onCategoryClick
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
                viewModel.reduce(AnalysisEvent.OnStartDateSelected(date, transactionType))
            },
            onDismiss = {
                viewModel.reduce(AnalysisEvent.HideDatePicker)
            },
            maxDate = DateHelper.parseDisplayDate(state.endDate)
        )
    }

    if (state.showEndDatePicker) {
        FADatePicker(
            selectedDate = DateHelper.parseDisplayDate(state.endDate),
            onDateSelected = { date ->
                viewModel.reduce(AnalysisEvent.OnEndDateSelected(date, transactionType))
            },
            onDismiss = {
                viewModel.reduce(AnalysisEvent.HideDatePicker)
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
                viewModel.reduce(AnalysisEvent.LoadAnalysis(transactionType))
            },
            onDismiss = {
                viewModel.reduce(AnalysisEvent.HideErrorDialog)
            }
        )
    }
}