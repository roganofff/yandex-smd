package quo.yandex.financialawareness.expenses.impl.ui.screen.history

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
import quo.yandex.financialawareness.expenses.impl.R
import quo.yandex.financialawareness.util.result.FailureReason
import quo.yandex.financialawareness.expenses.impl.domain.usecase.GetExpensesByPeriodUseCase
import quo.yandex.financialawareness.expenses.impl.ui.screen.history.state.ExpensesHistoryEffect
import quo.yandex.financialawareness.expenses.impl.ui.screen.history.state.ExpensesHistoryEvent
import quo.yandex.financialawareness.expenses.impl.ui.screen.history.state.ExpensesHistoryState
import quo.yandex.financialawareness.expenses.impl.ui.mapper.ExpenseUIMapper
import quo.yandex.financialawareness.transactions.api.model.TransactionType
import quo.yandex.financialawareness.transactions.api.usecase.GetTransactionsByPeriodUseCase
import quo.yandex.financialawareness.ui.base.BaseViewModel
import quo.yandex.financialawareness.util.DateHelper
import quo.yandex.financialawareness.util.result.Result
import javax.inject.Inject

@HiltViewModel
class ExpensesHistoryViewModel @Inject constructor(
    private val getTransactionsByPeriodUseCase: GetTransactionsByPeriodUseCase,
    private val expenseUIMapper: ExpenseUIMapper,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<ExpensesHistoryState, ExpensesHistoryEvent, ExpensesHistoryEffect>() {

    override val _state = MutableStateFlow(ExpensesHistoryState())
    override val state: StateFlow<ExpensesHistoryState> = _state.asStateFlow()

    override val _effect = Channel<ExpensesHistoryEffect>(Channel.UNLIMITED)
    override val effect = _effect.receiveAsFlow()

    private var loadExpensesJob: Job? = null

    override fun reduce(event: ExpensesHistoryEvent) {
        when (event) {
            is ExpensesHistoryEvent.ShowStartDatePicker -> {
                _state.update { it.copy(showStartDatePicker = true) }
            }
            is ExpensesHistoryEvent.ShowEndDatePicker -> {
                _state.update { it.copy(showEndDatePicker = true) }
            }
            is ExpensesHistoryEvent.HideDatePicker -> {
                _state.update {
                    it.copy(
                        showStartDatePicker = false,
                        showEndDatePicker = false
                    )
                }
            }
            is ExpensesHistoryEvent.OnStartDateSelected -> {
                val formattedDate = DateHelper.formatDateForDisplay(event.date) ?: return
                _state.update {
                    it.copy(
                        startDate = formattedDate,
                        showStartDatePicker = false
                    )
                }
                loadExpensesByPeriod()
            }
            is ExpensesHistoryEvent.OnEndDateSelected -> {
                val formattedDate = DateHelper.formatDateForDisplay(event.date) ?: return
                _state.update {
                    it.copy(
                        endDate = formattedDate,
                        showEndDatePicker = false
                    )
                }
                loadExpensesByPeriod()
            }
            ExpensesHistoryEvent.HideErrorDialog ->
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
            ExpensesHistoryEvent.LoadExpenses -> {
                _state.update {
                    it.copy(
                        showErrorDialog = null
                    )
                }
                loadExpensesByPeriod()
            }
        }
    }

    private fun loadExpensesByPeriod() {
        loadExpensesJob?.cancel()

        loadExpensesJob = launchTask {
            _state.update { it.copy(isLoading = true) }
            delay(1000)

            when (val result = getTransactionsByPeriodUseCase(
                startDate = DateHelper.parseDisplayDate(state.value.startDate),
                endDate = DateHelper.parseDisplayDate(state.value.endDate),
                transactionType = TransactionType.INCOME
            )) {
                is Result.Success -> {
                    val expenses = result.data
                    val totalSum = expenses.sumOf { it.amount }
                    _state.update {
                        it.copy(
                            isLoading = false,
                            expenses = expenseUIMapper.mapList(expenses),
                            total = expenseUIMapper.mapTotal(totalSum, expenses.firstOrNull()?.account?.currency ?: "RUB")
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
                expenses = emptyList(),
                showErrorDialog = message
            )
        }
    }
}