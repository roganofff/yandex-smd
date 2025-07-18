package quo.yandex.financialawareness.income.impl.ui.screen.today.state

import androidx.compose.runtime.Immutable
import quo.yandex.financialawareness.income.impl.ui.model.IncomeUIModel

@Immutable
data class IncomeTodayState(
    val total: String = "0.00 ₽",
    val isLoading: Boolean = false,
    val currency: String = "",
    val income: List<IncomeUIModel> = emptyList(),
    val showErrorDialog: String? = null
)