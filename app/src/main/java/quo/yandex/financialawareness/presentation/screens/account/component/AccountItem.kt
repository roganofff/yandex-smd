package quo.yandex.financialawareness.presentation.screens.account.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.screens.account.model.AccountUIModel
import quo.yandex.financialawareness.presentation.ui.components.FADivider
import quo.yandex.financialawareness.presentation.ui.components.FAListItem
import quo.yandex.financialawareness.presentation.ui.components.FAShimmerListItem

@Composable
fun AccountItem(account: AccountUIModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        FAListItem(
            title = if (account.name.isEmpty()) stringResource(R.string.account) else account.name,
            trailIcon = R.drawable.ic_more_vert,
            leadIcon = "\uD83D\uDCB0",
            trailTitle = "${account.balance} ${account.currency.symbol}",
            height = 60.dp,
            isLeading = true,
        )

        FADivider()

        FAListItem(
            title = stringResource(R.string.valute),
            trailIcon = R.drawable.ic_more_vert,
            trailTitle = account.currency.symbol,
            height = 60.dp,
            isLeading = true,
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
            height = 60.dp,
            backgroundColor = colorScheme.secondaryContainer
        )

        FADivider()

        FAShimmerListItem(
            showTrailingTitle = true,
            showTrailingIcon = true,
            height = 60.dp,
            backgroundColor = colorScheme.secondaryContainer
        )
    }
}