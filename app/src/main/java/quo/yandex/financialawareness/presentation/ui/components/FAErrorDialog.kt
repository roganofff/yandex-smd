package quo.yandex.financialawareness.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import quo.yandex.financialawareness.R

@Composable
fun FAErrorDialog(
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.error),
    message: String,
    onDismiss: () -> Unit,
    confirmButtonText: String = stringResource(R.string.ok),
    dismissButtonText: String? = null,
    onConfirm: (() -> Unit)? = null,
    backgroundColor: Color = colorScheme.secondaryContainer,
    contentColor: Color = colorScheme.onSurface,
    iconTint: Color = colorScheme.onSurface,
    shape: Shape = RoundedCornerShape(16.dp)
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = modifier,
            color = backgroundColor,
            contentColor = contentColor,
            shape = shape
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_info),
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Text(
                        modifier = Modifier.padding(
                            PaddingValues(
                                vertical = 16.dp,
                                horizontal = 0.dp
                            )
                        ),
                        text = title,
                        style = typography.titleLarge,
                        color = contentColor,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(PaddingValues(0.dp)),
                    text = message,
                    style = typography.bodyLarge,
                    color = contentColor.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    dismissButtonText?.let { text ->
                        TextButton(onClick = onDismiss) {
                            Text(
                                modifier = Modifier.padding(PaddingValues(0.dp)),
                                text = dismissButtonText,
                                color = contentColor.copy(alpha = 0.7f),
                                style = typography.bodyLarge,
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    TextButton(
                        onClick = { onConfirm?.invoke() ?: onDismiss() },
                    ) {
                        Text(
                            modifier = Modifier.padding(PaddingValues(0.dp)),
                            text = confirmButtonText,
                            color = colorScheme.primary,
                            style = typography.bodyLarge,
                        )
                    }
                }
            }
        }
    }
}