package quo.yandex.financialawareness.income.impl.ui.screen.today.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import quo.yandex.financialawareness.ui.component.FAShimmerListItem

@Composable
fun IncomeTodayShimmerList(
    modifier: Modifier = Modifier,
    itemCount: Int = 6,
) {
    LazyColumn {
        items(count = itemCount) { index ->
            FAShimmerListItem(
                showSubtitle = index % 2 == 1,
                showTrailingTitle = true,
                showTrailingIcon = true
            )
            HorizontalDivider()
        }
    }
}