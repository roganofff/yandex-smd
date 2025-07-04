package quo.yandex.financialawareness.presentation.screens.categories

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.ui.components.FATopBar

@Composable
fun CategoriesTopBar() = FATopBar(title = stringResource(R.string.my_categories))