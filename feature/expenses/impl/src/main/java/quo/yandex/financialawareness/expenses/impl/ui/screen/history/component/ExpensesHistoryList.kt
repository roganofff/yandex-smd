package quo.yandex.financialawareness.expenses.impl.ui.screen.history.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.expenses.impl.R
import quo.yandex.financialawareness.expenses.impl.ui.model.ExpenseUIModel
import quo.yandex.financialawareness.ui.component.FAEmojiIcon
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAListItem

@Composable
fun ExpensesHistoryList(
    expenses: List<ExpenseUIModel>,
    onExpenseClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            count = expenses.size,
            key = { index -> expenses[index].id }
        ) { index ->
            val expense = expenses[index]
            FAListItem(
                leadingIcon = { 
                    FAEmojiIcon(
                        emoji = expense.emoji
                    ) 
                },
                title = expense.name,
                subtitle = expense.comment,
                trailingTitle = expense.amount,
                trailingSubtitle = expense.date,
                trailingIcon = {
                    FAIcon(
                        painter = painterResource(R.drawable.ic_more),
                        contentDescription = stringResource(R.string.more),
                    )
                },
                onClick = { onExpenseClick(expense.id) }
            )
            HorizontalDivider()
        }
    }
}