package quo.yandex.financialawareness.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.ui.R
import quo.yandex.financialawareness.ui.theme.Providers

@Composable
fun FAPeriodItem(
    startDate: String,
    endDate: String,
    onStartDateClick: () -> Unit,
    onEndDateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        FAListItem(
            title = stringResource(R.string.start),
            trailingTitle = startDate,
            onClick = onStartDateClick,
            height = Providers.spacing.xxl,
            backgroundColor = Providers.color.secondaryContainer
        )

        HorizontalDivider()

        FAListItem(
            title = stringResource(R.string.end),
            trailingTitle = endDate,
            onClick = onEndDateClick,
            height = Providers.spacing.xxl,
            backgroundColor = Providers.color.secondaryContainer
        )
    }
}