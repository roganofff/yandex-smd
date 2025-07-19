package quo.yandex.financialawareness.analysis.impl.ui.screen.state

import androidx.compose.runtime.Immutable
import quo.yandex.financialawareness.analysis.impl.ui.model.CategoryAnalysisUIModel
import quo.yandex.financialawareness.util.DateHelper

@Immutable
data class AnalysisState(
    val isLoading: Boolean = false,
    val startDate: String = DateHelper.getCurrentMonthStartDisplayFormat(),
    val endDate: String = DateHelper.getTodayDisplayFormat(),
    val total: String = "0.00 ₽",
    val categories: List<CategoryAnalysisUIModel> = emptyList(),
    val showStartDatePicker: Boolean = false,
    val showEndDatePicker: Boolean = false,
    val showErrorDialog: String? = null
)