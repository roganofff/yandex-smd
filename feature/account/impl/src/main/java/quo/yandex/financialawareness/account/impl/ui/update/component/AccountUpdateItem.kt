package quo.yandex.financialawareness.account.impl.ui.update.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import quo.yandex.financialawareness.account.impl.R
import quo.yandex.financialawareness.account.impl.ui.model.AccountUIModel
import quo.yandex.financialawareness.ui.component.FAEditableListItem
import quo.yandex.financialawareness.ui.component.FAEmojiIcon
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAListItem
import quo.yandex.financialawareness.ui.theme.Providers
import quo.yandex.financialawareness.ui.util.pattern.BALANCE_PATTERN

@Composable
fun AccountUpdateItem(
    account: AccountUIModel,
    onNameChange: (String) -> Unit,
    onBalanceChange: (String) -> Unit,
    onCurrencyClick: () -> Unit
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        FAEditableListItem(
            leadingIcon = {
                FAEmojiIcon(
                    emoji = "\uD83D\uDCB0",
                )
            },
            placeholder = stringResource(R.string.account),
            title = stringResource(R.string.account_name),
            value = account.name,
            onValueChange = onNameChange,
            height = Providers.spacing.xxl,
        )

        HorizontalDivider()

        FAEditableListItem(
            title = stringResource(R.string.balance),
            value = account.balance,
            placeholder = "0.00",
            keyboardType = KeyboardType.Number,
            onValueChange = onBalanceChange,
            inputFilter = { newValue ->
                newValue.isEmpty() || BALANCE_PATTERN.matches(newValue)
            },
            height = Providers.spacing.xxl,
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
            onClick = { onCurrencyClick() },
            height = Providers.spacing.xxl,
        )

        HorizontalDivider()
    }
}