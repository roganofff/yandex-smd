package quo.yandex.financialawareness.transactions.impl.ui.screen.create.state

import androidx.compose.runtime.Immutable
import quo.yandex.financialawareness.transactions.impl.ui.model.CategoryUIModel
import quo.yandex.financialawareness.transactions.impl.ui.model.TransactionUIModel

@Immutable
data class TransactionCreateState(
    val isLoading: Boolean = false,
    val showDatePicker: Boolean = false,
    val showTimePicker: Boolean = false,
    val showErrorDialog: String? = null,
    val transaction: TransactionUIModel = TransactionUIModel(),
    val categories: List<CategoryUIModel> = emptyList(),
    val showBottomSheet: Boolean = false
)