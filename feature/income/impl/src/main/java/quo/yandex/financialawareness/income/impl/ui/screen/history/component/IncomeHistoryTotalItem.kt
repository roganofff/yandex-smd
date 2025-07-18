package quo.yandex.financialawareness.income.impl.ui.screen.history.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.income.impl.R
import quo.yandex.financialawareness.ui.component.FAListItem
import quo.yandex.financialawareness.ui.theme.Providers

@Composable
fun IncomeHistoryTotalItem(
    totalSum: String,
    modifier: Modifier = Modifier
) {
    FAListItem(
        title = stringResource(R.string.sum),
        trailingTitle = totalSum,
        onClick = { },
        height = Providers.spacing.xxl,
        backgroundColor = Providers.color.secondaryContainer,
        modifier = modifier
    )
}
