package quo.yandex.financialawareness.account.impl.ui.update.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import quo.yandex.financialawareness.ui.component.FAShimmerListItem
import quo.yandex.financialawareness.ui.theme.Providers

@Composable
fun AccountUpdateShimmerItem() {
    Column(modifier = Modifier.fillMaxWidth()) {
        FAShimmerListItem(
            showLeadingIcon = true,
            showTrailingTitle = true,
            height = Providers.spacing.xxl,
        )

        HorizontalDivider()

        FAShimmerListItem(
            showTrailingTitle = true,
            height = Providers.spacing.xxl,
        )

        HorizontalDivider()

        FAShimmerListItem(
            showTrailingTitle = true,
            showTrailingIcon = true,
            height = Providers.spacing.xxl,
        )
    }
}