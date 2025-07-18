package quo.yandex.financialawareness.income.impl.ui.screen.today

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import quo.yandex.financialawareness.domain.provider.ResourceProvider
import quo.yandex.financialawareness.income.impl.R
import quo.yandex.financialawareness.util.result.FailureReason
import quo.yandex.financialawareness.util.result.Result
import quo.yandex.financialawareness.income.impl.domain.usecase.GetTodayIncomeUseCase
import quo.yandex.financialawareness.income.impl.ui.mapper.IncomeUIMapper
import quo.yandex.financialawareness.income.impl.ui.screen.today.state.IncomeTodayEffect
import quo.yandex.financialawareness.income.impl.ui.screen.today.state.IncomeTodayEvent
import quo.yandex.financialawareness.income.impl.ui.screen.today.state.IncomeTodayState
import quo.yandex.financialawareness.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class IncomeTodayViewModel @Inject constructor(
    private val getTodayIncomeUseCase: GetTodayIncomeUseCase,
    private val incomeUIMapper: IncomeUIMapper,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<IncomeTodayState, IncomeTodayEvent, IncomeTodayEffect>() {

    override val _state = MutableStateFlow(IncomeTodayState())
    override val state: StateFlow<IncomeTodayState> = _state.asStateFlow()

    override val _effect = Channel<IncomeTodayEffect>(Channel.UNLIMITED)
    override val effect = _effect.receiveAsFlow()

    private var loadIncomeJob: Job? = null

    override fun reduce(event: IncomeTodayEvent) {
        when (event) {
            IncomeTodayEvent.HideErrorDialog ->
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
            IncomeTodayEvent.LoadIncome -> {
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
                loadTodayIncome()
            }
        }
    }

    private fun loadTodayIncome() {
        loadIncomeJob?.cancel()

        loadIncomeJob = launchTask {
            _state.update { it.copy(isLoading = true) }
            delay(1000)

            when (val result = getTodayIncomeUseCase()) {
                is Result.Success -> {
                    val income = result.data
                    val totalSum = income.sumOf { it.amount }
                    _state.update {
                        it.copy(
                            isLoading = false,
                            income = incomeUIMapper.mapList(income),
                            total = incomeUIMapper.mapTotal(totalSum, income.firstOrNull()?.account?.currency ?: "RUB"),
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
                income = emptyList(),
                showErrorDialog = message
            )
        }
    }
}
