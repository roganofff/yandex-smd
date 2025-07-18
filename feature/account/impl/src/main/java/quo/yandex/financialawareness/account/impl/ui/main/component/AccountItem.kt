package quo.yandex.financialawareness.account.impl.ui.main.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.account.impl.R
import quo.yandex.financialawareness.ui.component.FAEmojiIcon
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAListItem
import quo.yandex.financialawareness.ui.theme.Providers
import quo.yandex.financialawareness.account.impl.ui.model.AccountUIModel

@Composable
fun AccountItem(account: AccountUIModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        FAListItem(
            leadingIcon = {
                FAEmojiIcon(
                    emoji = "\uD83D\uDCB0",
                    backgroundColor = Color.White
                )
            },
            title = account.name.ifEmpty { stringResource(R.string.account) },
            trailingTitle = "${account.balance} ${account.currency.symbol}",
            trailingIcon = {
                FAIcon(
                    painter = painterResource(R.drawable.ic_more),
                    contentDescription = stringResource(R.string.more),
                )
            },
            onClick = { },
            height = Providers.spacing.xxl,
            backgroundColor = Providers.color.secondaryContainer
        )

        HorizontalDivider()

        FAListItem(
            title = stringResource(R.string.valute),
            trailingTitle = account.currency.symbol,
            trailingIcon = {
                FAIcon(
                    painter = painterResource(R.drawable.ic_more),
                    contentDescription = stringResource(R.string.more),
                )
            },
            onClick = { },
            height = Providers.spacing.xxl,
            backgroundColor = Providers.color.secondaryContainer
        )
    }
}