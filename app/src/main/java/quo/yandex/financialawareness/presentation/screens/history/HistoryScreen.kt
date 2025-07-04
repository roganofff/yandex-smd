package quo.yandex.financialawareness.presentation.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import quo.yandex.financialawareness.presentation.ui.components.FADivider
import quo.yandex.financialawareness.presentation.ui.components.FAListItem
import quo.yandex.financialawareness.util.getCurrentMonth

@Preview
@Composable
fun HistoryScreen(
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
        item {
            FAListItem(
                title = "Начало",
                trailTitle = getCurrentMonth(),
                isLeading = true,
                isEmojiIcon = false,
            )
            FADivider()
            FAListItem(
                title = "Конец",
                trailTitle = "Сегодня",
                isLeading = true,
                isEmojiIcon = false,
            )
            FADivider()
            FAListItem(
                title = "Сумма",
                trailTitle = "0.00 ₽",
                isLeading = true,
                isEmojiIcon = false,
            )
            FADivider()
        }
    }
}

