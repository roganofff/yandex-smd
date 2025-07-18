package quo.yandex.financialawareness.categories.impl.ui.state

import androidx.compose.runtime.Immutable
import quo.yandex.financialawareness.categories.impl.ui.model.CategoryDetailsUIModel

@Immutable
data class CategoriesState(
    val isLoading: Boolean = false,
    val input: String = "",
    val categories: List<CategoryDetailsUIModel> = emptyList(),
    val showErrorDialog: String? = null
)
