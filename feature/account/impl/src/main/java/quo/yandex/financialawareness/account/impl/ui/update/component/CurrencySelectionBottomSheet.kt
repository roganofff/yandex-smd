package quo.yandex.financialawareness.account.impl.ui.update.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import quo.yandex.financialawareness.account.impl.ui.model.CurrencyUIModel
import quo.yandex.financialawareness.ui.R
import quo.yandex.financialawareness.ui.component.FAIcon
import quo.yandex.financialawareness.ui.component.FAListItem
import quo.yandex.financialawareness.ui.theme.Providers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencySelectionBottomSheet(
    currencies: List<CurrencyUIModel>,
    onCurrencySelected: (CurrencyUIModel) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = rememberModalBottomSheetState(),
        containerColor = Providers.color.surface,
    ) {
        Column {
            LazyColumn(
                modifier = modifier
                    .weight(1f, fill = false)
                    .heightIn(max = 320.dp)
            ) {
                items(
                    count = currencies.size,
                    key = { index -> currencies[index].currency }
                ) { index ->
                    val currency = currencies[index]
                    FAListItem(
                        leadingIcon = {
                            FAIcon(
                                painter = painterResource(currency.iconResId)
                            )
                        },
                        title = currency.name,
                        onClick = { onCurrencySelected(currency) }
                    )
                    HorizontalDivider()
                }
            }

            FAListItem(
                leadingIcon = {
                    FAIcon(
                        painter = painterResource(R.drawable.ic_exit),
                        tint = Providers.color.onError,
                        contentDescription = stringResource(R.string.exit),
                    )
                },
                title = stringResource(R.string.close),
                backgroundColor = Providers.color.error,
                mainColor = Providers.color.onError,
                onClick = { onDismiss() }
            )

            HorizontalDivider()
        }
    }
}