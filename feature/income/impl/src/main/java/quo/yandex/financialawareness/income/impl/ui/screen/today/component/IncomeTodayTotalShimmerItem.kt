package quo.yandex.financialawareness.income.impl.ui.screen.today.component

import androidx.compose.runtime.Composable
import quo.yandex.financialawareness.ui.component.FAShimmerListItem
import quo.yandex.financialawareness.ui.theme.Providers

@Composable
fun IncomeTodayTotalShimmerItem() {
    FAShimmerListItem(
        showTrailingTitle = true,
        height = Providers.spacing.xxl,
        backgroundColor = Providers.color.secondaryContainer
    )
}