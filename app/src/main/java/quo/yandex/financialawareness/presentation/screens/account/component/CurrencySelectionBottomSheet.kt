package quo.yandex.financialawareness.presentation.screens.account.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import quo.yandex.financialawareness.R
import quo.yandex.financialawareness.presentation.screens.account.model.CurrencyUIModel
import quo.yandex.financialawareness.presentation.ui.components.FADivider
import quo.yandex.financialawareness.presentation.ui.components.FAListItem

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
        containerColor = colorScheme.surface,
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
                        leadIcon = currency.iconResId,
                        title = currency.name,
                        onClick = { onCurrencySelected(currency) }
                    )
                    FADivider()
                }
            }

            FAListItem(
                leadIcon = R.drawable.ic_exit,
                title = stringResource(R.string.close),
                mainColor = colorScheme.error,
                backgroundColor = colorScheme.onError,
                onClick = { onDismiss() },
                isLeading = true
            )

            FADivider()
        }
    }
}