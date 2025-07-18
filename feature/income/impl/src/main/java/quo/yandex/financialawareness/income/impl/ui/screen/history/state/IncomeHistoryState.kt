package quo.yandex.financialawareness.income.impl.ui.screen.history.state

import androidx.compose.runtime.Immutable
import quo.yandex.financialawareness.income.impl.ui.model.IncomeUIModel
import quo.yandex.financialawareness.util.DateHelper

@Immutable
data class IncomeHistoryState(
    val isLoading: Boolean = false,
    val startDate: String = DateHelper.getCurrentMonthStartDisplayFormat(),
    val endDate: String = DateHelper.getTodayDisplayFormat(),
    val total: String = "0.00 ₽",
    val income: List<IncomeUIModel> = emptyList(),
    val showStartDatePicker: Boolean = false,
    val showEndDatePicker: Boolean = false,
    val showErrorDialog: String? = null
)