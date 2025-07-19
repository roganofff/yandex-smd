package quo.yandex.financialawareness.analysis.impl.ui.screen

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import quo.yandex.financialawareness.analysis.impl.domain.usecase.GetCategoriesWithSortedPercentageUseCase
import quo.yandex.financialawareness.analysis.impl.ui.mapper.CategoryAnalysisUIMapper
import quo.yandex.financialawareness.analysis.impl.ui.screen.state.AnalysisEffect
import quo.yandex.financialawareness.analysis.impl.ui.screen.state.AnalysisEvent
import quo.yandex.financialawareness.analysis.impl.ui.screen.state.AnalysisState
import quo.yandex.financialawareness.domain.provider.ResourceProvider
import quo.yandex.financialawareness.transactions.api.model.TransactionType
import quo.yandex.financialawareness.ui.R
import quo.yandex.financialawareness.ui.base.BaseViewModel
import quo.yandex.financialawareness.util.DateHelper
import quo.yandex.financialawareness.util.result.FailureReason
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val getCategoriesWithSortedPercentageUseCase: GetCategoriesWithSortedPercentageUseCase,
    private val categoryAnalysisUIMapper: CategoryAnalysisUIMapper,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<AnalysisState, AnalysisEvent, AnalysisEffect>() {

    override val _state = MutableStateFlow(AnalysisState())
    override val state: StateFlow<AnalysisState> = _state.asStateFlow()

    override val _effect = Channel<AnalysisEffect>(Channel.UNLIMITED)
    override val effect = _effect.receiveAsFlow()

    private var loadIncomeJob: Job? = null

    override fun reduce(event: AnalysisEvent) {
        when (event) {
            is AnalysisEvent.ShowStartDatePicker -> {
                _state.update { it.copy(showStartDatePicker = true) }
            }
            is AnalysisEvent.ShowEndDatePicker -> {
                _state.update { it.copy(showEndDatePicker = true) }
            }
            is AnalysisEvent.HideDatePicker -> {
                _state.update {
                    it.copy(
                        showStartDatePicker = false,
                        showEndDatePicker = false
                    )
                }
            }
            is AnalysisEvent.OnStartDateSelected -> {
                val formattedDate = DateHelper.formatDateForDisplay(event.date) ?: return
                _state.update {
                    it.copy(
                        startDate = formattedDate,
                        showStartDatePicker = false
                    )
                }
                loadAnalysisByPeriod(event.transactionType)
            }
            is AnalysisEvent.OnEndDateSelected -> {
                val formattedDate = DateHelper.formatDateForDisplay(event.date) ?: return
                _state.update {
                    it.copy(
                        endDate = formattedDate,
                        showEndDatePicker = false
                    )
                }
                loadAnalysisByPeriod(event.transactionType)
            }
            is AnalysisEvent.HideErrorDialog ->
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
            is AnalysisEvent.LoadAnalysis -> {
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
                loadAnalysisByPeriod(event.transactionType)
            }
        }
    }

    private fun loadAnalysisByPeriod(transactionType: TransactionType) {
        loadIncomeJob?.cancel()

        loadIncomeJob = launchTask {
            _state.update { it.copy(isLoading = true) }
            delay(1000)

            when (val result = getCategoriesWithSortedPercentageUseCase(
                startDate = DateHelper.parseDisplayDate(state.value.startDate),
                endDate = DateHelper.parseDisplayDate(state.value.endDate),
                transactionType = transactionType
            )) {
                is Result.Success -> {
                    val categories = result.data
                    val totalSum = categories.sumOf { it.amount }
                    _state.update {
                        it.copy(
                            isLoading = false,
                            categories = categoryAnalysisUIMapper.mapList(categories),
                            total = categoryAnalysisUIMapper.mapTotal(totalSum, categories.firstOrNull()?.currency ?: "RUB")
                        )
                    }
                }
                is Result.Failure -> {
                    handleFailure(result.reason)
                }
            }
        }
    }

    private fun handleFailure(failureReason: FailureReason) {
        val message = when (failureReason) {
            is FailureReason.Network -> resourceProvider.getString(R.string.failure_network)
            is FailureReason.Unauthorized -> resourceProvider.getString(R.string.failure_unauthorized)
            is FailureReason.Server -> resourceProvider.getString(R.string.failure_server)
            is FailureReason.BadRequest -> resourceProvider.getString(R.string.failure_bad_request)
            else -> resourceProvider.getString(R.string.failure_unknown)
        }
        _state.update {
            it.copy(
                isLoading = false,
                categories = emptyList(),
                showErrorDialog = message
            )
        }
    }
}