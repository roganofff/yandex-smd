package quo.yandex.financialawareness.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import quo.yandex.financialawareness.ui.R
import quo.yandex.financialawareness.ui.theme.Providers

@Composable
fun FAErrorDialog(
    title: String = stringResource(R.string.error),
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    confirmButtonText: String = stringResource(R.string.ok),
    dismissButtonText: String? = null,
    onConfirm: (() -> Unit)? = null,
    backgroundColor: Color = Providers.color.secondaryContainer,
    contentColor: Color = Providers.color.onSurface,
    iconTint: Color = Providers.color.onSurface,
    shape: Shape = Providers.shape.l
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = modifier,
            color = backgroundColor,
            contentColor = contentColor,
            shape = shape
        ) {
            Column(
                modifier = Modifier.padding(Providers.spacing.l)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FAIcon(
                        painter = painterResource(R.drawable.ic_info),
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.padding(end = Providers.spacing.m)
                    )

                    FAText(
                        text = title,
                        style = Providers.typography.bodyXL,
                        color = contentColor,
                        contentPadding = PaddingValues(
                            vertical = Providers.spacing.m,
                            horizontal = Providers.spacing.none
                        )
                    )
                }

                Spacer(modifier = Modifier.height(Providers.spacing.m))

                FAText(
                    text = message,
                    style = Providers.typography.bodyL,
                    color = contentColor.copy(alpha = 0.8f),
                    contentPadding = PaddingValues(Providers.spacing.none)
                )

                Spacer(modifier = Modifier.height(Providers.spacing.l))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    dismissButtonText?.let { text ->
                        TextButton(onClick = onDismiss) {
                            FAText(
                                text = dismissButtonText,
                                color = contentColor.copy(alpha = 0.7f),
                                style = Providers.typography.bodyL,
                                contentPadding = PaddingValues(Providers.spacing.none)
                            )
                        }
                        Spacer(modifier = Modifier.width(Providers.spacing.s))
                    }

                    TextButton(
                        onClick = { onConfirm?.invoke() ?: onDismiss() },
                    ) {
                        FAText(
                            text = confirmButtonText,
                            color = Providers.color.primary,
                            style = Providers.typography.bodyL,
                            contentPadding = PaddingValues(Providers.spacing.none)
                        )
                    }
                }
            }
        }
    }
}