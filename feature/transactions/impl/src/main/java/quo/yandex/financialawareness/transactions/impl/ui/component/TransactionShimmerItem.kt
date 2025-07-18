package quo.yandex.financialawareness.transactions.impl.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import quo.yandex.financialawareness.ui.component.FAShimmerListItem

@Composable
fun TransactionShimmerItem() {
    Column(modifier = Modifier.fillMaxWidth()) {
        FAShimmerListItem(
            showTrailingTitle = true,
            showTrailingIcon = true,
        )

        HorizontalDivider()

        FAShimmerListItem(
            showTrailingTitle = true,
            showTrailingIcon = true,
        )

        HorizontalDivider()

        FAShimmerListItem(
            showTrailingTitle = true,
        )

        HorizontalDivider()

        FAShimmerListItem(
            showTrailingTitle = true,
        )

        HorizontalDivider()

        FAShimmerListItem(
            showTrailingTitle = true,
        )

        HorizontalDivider()

        FAShimmerListItem()

        HorizontalDivider()
    }
}