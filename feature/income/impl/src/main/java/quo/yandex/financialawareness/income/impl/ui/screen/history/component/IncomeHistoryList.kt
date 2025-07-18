package quo.yandex.financialawareness.income.impl.ui.screen.history.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.income.impl.R
import quo.yandex.financialawareness.income.impl.ui.model.IncomeUIModel
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAListItem

@Composable
fun IncomeHistoryList(
    expenses: List<IncomeUIModel>,
    onIncomeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            count = expenses.size,
            key = { index -> expenses[index].id }
        ) { index ->
            val income = expenses[index]
            FAListItem(
                title = income.name,
                subtitle = income.comment,
                trailingTitle = income.amount,
                trailingSubtitle = income.date,
                trailingIcon = {
                    FAIcon(
                        painter = painterResource(R.drawable.ic_more),
                        contentDescription = stringResource(R.string.more),
                    )
                },
                onClick = { onIncomeClick(income.id) }
            )
            HorizontalDivider()
        }
    }
}