package quo.yandex.financialawareness.expenses.impl.ui.screen.history.state

import androidx.compose.runtime.Immutable
import quo.yandex.financialawareness.expenses.impl.ui.model.ExpenseUIModel
import quo.yandex.financialawareness.util.DateHelper

@Immutable
data class ExpensesHistoryState(
    val isLoading: Boolean = false,
    val startDate: String = DateHelper.getCurrentMonthStartDisplayFormat(),
    val endDate: String = DateHelper.getTodayDisplayFormat(),
    val total: String = "0.00 ₽",
    val expenses: List<ExpenseUIModel> = emptyList(),
    val showStartDatePicker: Boolean = false,
    val showEndDatePicker: Boolean = false,
    val showErrorDialog: String? = null
)