package quo.yandex.financialawareness.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quo.yandex.financialawareness.data.mock.provideCategoriesMockData
import quo.yandex.financialawareness.presentation.ui.components.ListItem
import quo.yandex.financialawareness.presentation.ui.components.SearchField

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(modifier: Modifier = Modifier) {
    var query by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2AE881),
                    titleContentColor = Color(0xFF1D1B20),
                ),
                title = {
                    Text(
                        "Мои статьи",
                        maxLines = 1,
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight(400),
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
            item {
                SearchField(query)
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color(0xFFCAC4D0)
                )
            }

            items(provideCategoriesMockData()) { item ->
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
}
