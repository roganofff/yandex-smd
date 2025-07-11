package quo.yandex.financialawareness.presentation.screens.account.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun EditableAccountItem(
    title: String,
    value: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    inputFilter: (String) -> Boolean = { true },
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    backgroundColor: Color = colorScheme.surface,
    mainColor: Color = colorScheme.onSurface,
    variantColor: Color = colorScheme.onSurfaceVariant,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    height: Dp = 60.dp
) {
    Surface(
        modifier = modifier,
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .height(height),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.let {
                Box(modifier = Modifier.padding(end = 16.dp)) {
                    it()
                }
            }

            Text(
                text = title,
                maxLines = 1,
                color = mainColor,
                modifier = Modifier.weight(1f)
            )

            BasicTextField(
                value = value,
                onValueChange = { newValue ->
                    if (inputFilter(newValue)) {
                        onValueChange(newValue)
                    }
                },
                textStyle = typography.bodyLarge.copy(
                    color = mainColor,
                    textAlign = TextAlign.End
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                ),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Box(contentAlignment = Alignment.CenterEnd) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = typography.bodyLarge.copy(
                                    color = variantColor,
                                    textAlign = TextAlign.End
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}