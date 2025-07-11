package quo.yandex.financialawareness.presentation.screens.account.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.screens.account.model.AccountUIModel
import quo.yandex.financialawareness.presentation.ui.components.FADivider
import quo.yandex.financialawareness.presentation.ui.components.FAListItem
import quo.yandex.financialawareness.presentation.ui.components.FAShimmerListItem
import quo.yandex.financialawareness.util.BALANCE_PATTERN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountUpdateItem(
    account: AccountUIModel,
    onNameChange: (String) -> Unit,
    onBalanceChange: (String) -> Unit,
    onCurrencyClick: () -> Unit
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        EditableAccountItem(
            leadingIcon = {
                Text(
                    text = "\uD83D\uDCB0",
                    fontSize = 16.sp,
                    color = colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            },
            placeholder = stringResource(R.string.account),
            title = stringResource(R.string.account_name),
            value = account.name,
            onValueChange = onNameChange,
        )

        FADivider()

        EditableAccountItem(
            title = stringResource(R.string.balance),
            value = account.balance,
            placeholder = "0.00",
            keyboardType = KeyboardType.Number,
            onValueChange = onBalanceChange,
            inputFilter = { newValue ->
                newValue.isEmpty() || BALANCE_PATTERN.matches(newValue)
            },
        )

        FADivider()

        FAListItem(
            title = stringResource(R.string.valute),
            trailTitle = account.currency.symbol,
            trailIcon = R.drawable.ic_more_vert,
            onClick = { onCurrencyClick() },
            height = 60.dp,
        )

        FADivider()
    }
}

@Composable
fun AccountUpdateShimmerItem() {
    Column(modifier = Modifier.fillMaxWidth()) {
        FAShimmerListItem(
            showLeadingIcon = true,
            showTrailingTitle = true,
            height = 60.dp,
        )

        FADivider()

        FAShimmerListItem(
            showTrailingTitle = true,
            height = 60.dp,
        )

        FADivider()

        FAShimmerListItem(
            showTrailingTitle = true,
            showTrailingIcon = true,
            height = 60.dp,
        )
    }
}