package quo.yandex.financialawareness.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(modifier: Modifier = Modifier) {
    val periods = listOf("сегодня", "неделю", "месяц", "год", "всё время")
    var selectedPeriod by rememberSaveable { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
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
                            "Доходы за ${periods[selectedPeriod]}",
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
            Column(
                modifier = Modifier.fillMaxSize().padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Income Screen")
            }
        }
    }
}