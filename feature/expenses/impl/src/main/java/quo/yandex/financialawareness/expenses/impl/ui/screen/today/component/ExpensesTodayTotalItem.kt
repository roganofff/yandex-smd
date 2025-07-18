package quo.yandex.financialawareness.expenses.impl.ui.screen.today.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.expenses.impl.R
import quo.yandex.financialawareness.ui.component.FAListItem
import quo.yandex.financialawareness.ui.theme.Providers

@Composable
fun ExpensesTodayTotalItem(
    totalSum: String,
    modifier: Modifier = Modifier
) {
    FAListItem(
        title = stringResource(R.string.total),
        trailingTitle = totalSum,
        onClick = { },
        height = Providers.spacing.xxl,
        backgroundColor = Providers.color.secondaryContainer,
        modifier = modifier
    )
}