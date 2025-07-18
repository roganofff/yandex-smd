package quo.yandex.financialawareness.transactions.impl.ui.component
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import quo.yandex.financialawareness.transactions.impl.R
import quo.yandex.financialawareness.transactions.impl.ui.model.TransactionUIModel
import quo.yandex.financialawareness.ui.component.FAEditableListItem
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAListItem
import quo.yandex.financialawareness.ui.util.pattern.BALANCE_PATTERN

@Composable
fun TransactionItem(
    transaction: TransactionUIModel,
    onAmountChange: (String) -> Unit,
    onCategoryClick: () -> Unit,
    onDateClick: () -> Unit,
    onTimeClick: () -> Unit,
    onCommentChange: (String) -> Unit,
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        FAListItem(
            title = stringResource(R.string.account),
            trailingTitle = transaction.account.name,
            trailingIcon = {
                FAIcon(
                    painter = painterResource(R.drawable.ic_more),
                    contentDescription = stringResource(R.string.more),
                )
            },
        )

        HorizontalDivider()

        FAListItem(
            title = stringResource(R.string.category),
            trailingTitle = transaction.category.name,
            trailingIcon = {
                FAIcon(
                    painter = painterResource(R.drawable.ic_more),
                    contentDescription = stringResource(R.string.more),
                )
            },
            onClick = { onCategoryClick() },
        )

        HorizontalDivider()

        FAEditableListItem(
            title = stringResource(R.string.sum),
            value = transaction.amount,
            trailingTitle = " ${transaction.account.currencySymbol}",
            placeholder = "0.00",
            keyboardType = KeyboardType.Number,
            onValueChange = onAmountChange,
            inputFilter = { newValue ->
                newValue.isEmpty() || BALANCE_PATTERN.matches(newValue)
            },
        )

        HorizontalDivider()

        FAListItem(
            title = stringResource(R.string.date),
            trailingTitle = transaction.date,
            onClick = { onDateClick() },
        )

        HorizontalDivider()

        FAListItem(
            title = stringResource(R.string.time),
            trailingTitle = transaction.time,
            onClick = { onTimeClick() },
        )

        HorizontalDivider()

        FAEditableListItem(
            value = transaction.comment,
            placeholder = stringResource(R.string.comment),
            onValueChange = onCommentChange,
        )

        HorizontalDivider()
    }
}