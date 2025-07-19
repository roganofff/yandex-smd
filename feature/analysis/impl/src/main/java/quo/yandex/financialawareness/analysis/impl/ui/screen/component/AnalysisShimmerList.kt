package quo.yandex.financialawareness.analysis.impl.ui.screen.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import quo.yandex.financialawareness.ui.component.FAShimmerListItem

@Composable
fun AnalysisShimmerList(
    modifier: Modifier = Modifier,
    itemCount: Int = 6,
) {
    LazyColumn(modifier = modifier) {
        items(count = itemCount) { index ->
            FAShimmerListItem(
                showLeadingIcon = true,
                showTrailingTitle = true,
                showTrailingSubtitle = true,
                showTrailingIcon = true
            )
            HorizontalDivider()
        }
    }
}