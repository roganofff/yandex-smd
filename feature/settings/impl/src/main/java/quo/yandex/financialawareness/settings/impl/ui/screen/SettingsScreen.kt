package quo.yandex.financialawareness.settings.impl.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAListItem
import quo.yandex.financialawareness.ui.theme.Providers
import androidx.compose.runtime.getValue
import quo.yandex.financialawareness.settings.impl.R
import quo.yandex.financialawareness.ui.component.FACenterAlignedTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val moreIcon: @Composable () -> Unit = {
        FAIcon(
            painter = painterResource(R.drawable.ic_details),
            contentDescription = stringResource(R.string.details),
        )
    }

    var isDarkMode by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        FACenterAlignedTopAppBar(
            title = stringResource(R.string.settings)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            FAListItem(
                title = stringResource(R.string.dark_theme),
                trailingIcon = {
                    Switch(
                        checked = isDarkMode,
                        onCheckedChange = { isDarkMode = it }
                    )
                },
                onClick = {
                    isDarkMode = !isDarkMode
                },
                height = Providers.spacing.xxl,
            )

            HorizontalDivider()

            FAListItem(
                title = stringResource(R.string.main_color),
                trailingIcon = moreIcon,
                onClick = {  },
                height = Providers.spacing.xxl,
            )

            HorizontalDivider()

            FAListItem(
                title = stringResource(R.string.sounds),
                trailingIcon = moreIcon,
                onClick = {  },
                height = Providers.spacing.xxl,
            )

            HorizontalDivider()

            FAListItem(
                title = stringResource(R.string.haptics),
                trailingIcon = moreIcon,
                onClick = { /* Navigate to haptics settings */ },
                height = Providers.spacing.xxl,
            )

            HorizontalDivider(color = Providers.color.outline.copy(alpha = 0.2f))

            FAListItem(
                title = stringResource(R.string.password),
                trailingIcon = moreIcon,
                onClick = { /* Navigate to password settings */ },
                height = Providers.spacing.xxl,
            )

            HorizontalDivider()

            FAListItem(
                title = stringResource(R.string.synchronization),
                trailingIcon = moreIcon,
                onClick = { /* Navigate to sync settings */ },
                height = Providers.spacing.xxl,
            )

            HorizontalDivider()

            FAListItem(
                title = stringResource(R.string.language),
                trailingIcon = moreIcon,
                onClick = { /* Navigate to language settings */ },
                height = Providers.spacing.xxl,
            )

            HorizontalDivider()

            FAListItem(
                title = stringResource(R.string.about_program),
                trailingIcon = moreIcon,
                onClick = { /* Navigate to about screen */ },
                height = Providers.spacing.xxl,
            )

            HorizontalDivider()
        }
    }
}