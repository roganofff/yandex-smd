package quo.yandex.financialawareness.presentation.screens.expenses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import quo.yandex.financialawareness.data.mock.provideExpensesMockData
import quo.yandex.financialawareness.presentation.ui.components.FloatingButton
import quo.yandex.financialawareness.presentation.ui.components.ListItem

@Preview
@Composable
fun ExpensesScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        items(provideExpensesMockData()) { item ->
            ListItem(
                title = item.title,
                comment = item.comment,
                price = item.price,
                leadIcon = item.leadIcon,
                trailIcon = item.trailIcon,
                isLeading = item.isLeading,
                isEmojiIcon = item.isEmojiIcon,
            )
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color(0xFFCAC4D0)
            )
        }
    }

    FloatingButton(modifier)
}
