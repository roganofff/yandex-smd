package quo.yandex.financialawareness.expenses.impl.ui.screen.today.state

import androidx.compose.runtime.Immutable
import quo.yandex.financialawareness.expenses.impl.ui.model.ExpenseUIModel

@Immutable
data class ExpensesTodayState(
    val total: String = "0.00 ₽",
    val isLoading: Boolean = false,
    val expenses: List<ExpenseUIModel> = emptyList(),
    val showErrorDialog: String? = null
)