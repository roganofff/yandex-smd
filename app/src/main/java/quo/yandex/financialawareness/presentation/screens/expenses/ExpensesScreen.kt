package quo.yandex.financialawareness.presentation.screens.expenses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import quo.yandex.financialawareness.data.mock.provideExpensesMockData
import quo.yandex.financialawareness.presentation.ui.components.FADivider
import quo.yandex.financialawareness.presentation.ui.components.FAFloatingButton
import quo.yandex.financialawareness.presentation.ui.components.FAListItem

@Preview
@Composable
fun ExpensesScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        items(provideExpensesMockData()) { item ->
            FAListItem(
                title = item.title,
                comment = item.comment,
                trailTitle = item.price,
                leadIcon = item.leadIcon,
                trailIcon = item.trailIcon,
                isLeading = item.isLeading,
                isEmojiIcon = item.isEmojiIcon,
            )
            FADivider()
        }
    }

    FAFloatingButton(modifier)
}
