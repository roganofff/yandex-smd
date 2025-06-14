package quo.yandex.financialawareness.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.data.provideExpensesMockData
import quo.yandex.financialawareness.presentation.ui.components.FloatingButton
import quo.yandex.financialawareness.presentation.ui.components.ListItem

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(modifier: Modifier = Modifier) {
    val periods = listOf("сегодня", "неделю", "месяц", "год", "всё время")
    var selectedPeriod by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2AE881),
                    titleContentColor = Color(0xFF1D1B20),
                ),
                title = {
                    Text(
                        "Расходы за ${periods[selectedPeriod]}",
                        maxLines = 1,
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            selectedPeriod = (selectedPeriod + 1) % periods.size
                        },
                        content = {
                            Image(
                                painterResource(R.drawable.ic_history),
                                contentDescription = "История",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                },
            )
        },
    ) { contentPadding ->
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
    }

    FloatingButton(modifier)
}
