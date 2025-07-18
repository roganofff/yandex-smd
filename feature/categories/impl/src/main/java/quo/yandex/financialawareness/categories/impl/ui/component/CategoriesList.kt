package quo.yandex.financialawareness.categories.impl.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import quo.yandex.financialawareness.categories.impl.ui.model.CategoryDetailsUIModel
import quo.yandex.financialawareness.ui.component.FAEmojiIcon
import quo.yandex.financialawareness.ui.component.FAListItem

@Composable
fun CategoriesList(
    categories: List<CategoryDetailsUIModel>,
    onCategoryClick: (CategoryDetailsUIModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            count = categories.size,
            key = { index -> categories[index].id }
        ) { index ->
            val category = categories[index]
            FAListItem(
                leadingIcon = {
                    FAEmojiIcon(
                        emoji = category.emoji,
                    )
                },
                title = category.name,
                onClick = { onCategoryClick(category) }
            )
            HorizontalDivider()
        }
    }
}