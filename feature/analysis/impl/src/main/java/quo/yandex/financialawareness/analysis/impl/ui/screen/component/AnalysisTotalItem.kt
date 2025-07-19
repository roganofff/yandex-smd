package quo.yandex.financialawareness.analysis.impl.ui.screen.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.ui.R
import quo.yandex.financialawareness.ui.component.FAListItem
import quo.yandex.financialawareness.ui.theme.Providers

@Composable
fun AnalysisTotalItem(
    totalSum: String,
    modifier: Modifier = Modifier
) {
    FAListItem(
        title = stringResource(R.string.sum),
        trailingTitle = totalSum,
        onClick = { },
        height = Providers.spacing.xxl,
        modifier = modifier
    )
}
