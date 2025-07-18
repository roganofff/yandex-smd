package quo.yandex.financialawareness.expenses.impl.ui.screen.history.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.expenses.impl.R
import quo.yandex.financialawareness.ui.component.FAShimmerListItem
import quo.yandex.financialawareness.ui.theme.Providers

@Composable
fun ExpensesHistoryTotalShimmerItem() {
    Column(modifier = Modifier.fillMaxWidth()) {
        FAShimmerListItem(
            title = stringResource(R.string.sum),
            showTrailingTitle = true,
            height = Providers.spacing.xxl,
            backgroundColor = Providers.color.secondaryContainer
        )
    }
}