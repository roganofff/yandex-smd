package quo.yandex.financialawareness.analysis.impl.ui.screen.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.analysis.impl.ui.model.CategoryAnalysisUIModel
import quo.yandex.financialawareness.ui.R
import quo.yandex.financialawareness.ui.component.FAEmojiIcon
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAListItem

@Composable
fun AnalysisList(
    categories: List<CategoryAnalysisUIModel>,
    onCategoryClick: (Int) -> Unit,
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
                        emoji = category.emoji
                    ) 
                },
                title = category.name,
                trailingTitle = category.percentage,
                trailingSubtitle = category.amount,
                trailingIcon = {
                    FAIcon(
                        painter = painterResource(R.drawable.ic_more),
                        contentDescription = stringResource(R.string.more),
                    )
                },
                onClick = { onCategoryClick(category.id) }
            )
            HorizontalDivider()
        }
    }
}