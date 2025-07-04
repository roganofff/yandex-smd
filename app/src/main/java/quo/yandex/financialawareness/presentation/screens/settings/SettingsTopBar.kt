package quo.yandex.financialawareness.presentation.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.ui.components.FATopBar

@Composable
fun SettingsTopBar() = FATopBar(title = stringResource(R.string.settings))