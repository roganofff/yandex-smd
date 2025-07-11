package quo.yandex.financialawareness.presentation.screens.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.screens.account.model.AccountUIModel
import quo.yandex.financialawareness.presentation.ui.components.FADivider
import quo.yandex.financialawareness.presentation.ui.components.FAListItem
import quo.yandex.financialawareness.presentation.ui.components.FAShimmerListItem

@Composable
fun AccountItem(account: AccountUIModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        FAListItem(
            title = account.name.ifEmpty { stringResource(R.string.account) },
            trailIcon = R.drawable.ic_more_vert,
            leadIcon = "\uD83D\uDCB0",
            trailTitle = "${account.balance} ${account.currency.symbol}",
            onClick = { },
        )

        FADivider()

        FAListItem(
            title = stringResource(R.string.valute),
            trailIcon = R.drawable.ic_more_vert,
            trailTitle = account.currency.symbol,
            onClick = { },
        )
    }
}

@Composable
fun AccountShimmerItem() {
    Column(modifier = Modifier.fillMaxWidth()) {
        FAShimmerListItem(
            showLeadingIcon = true,
            showTrailingTitle = true,
            showTrailingIcon = true,
            height = Providers.spacing.xxl,
            backgroundColor = Providers.color.secondaryContainer
        )

        HorizontalDivider()

        FAShimmerListItem(
            showTrailingTitle = true,
            showTrailingIcon = true,
            height = Providers.spacing.xxl,
            backgroundColor = Providers.color.secondaryContainer
        )
    }
}