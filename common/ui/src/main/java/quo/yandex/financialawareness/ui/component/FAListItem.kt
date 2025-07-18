package quo.yandex.financialawareness.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import quo.yandex.financialawareness.ui.theme.Providers

@Composable
fun FAListItem(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    title: String,
    subtitle: String? = null,
    trailingTitle: String? = null,
    trailingSubtitle: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    backgroundColor: Color = Providers.color.surface,
    mainColor: Color = Providers.color.onSurface,
    variantColor: Color = Providers.color.onSurfaceVariant,
    onClick: (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(horizontal = Providers.spacing.m),
    height: Dp = Providers.spacing.xxxl
) {
    val interactionSource = remember { MutableInteractionSource() }
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    } else {
        Modifier
    }

    Surface(
        modifier = modifier.then(clickableModifier),
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
                Box(modifier = Modifier.padding(end = Providers.spacing.m)) {
                    it()
                }
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                if (subtitle.isNullOrEmpty()) {
                    FAText(
                        text = title,
                        maxLines = 1,
                        color = mainColor
                    )
                } else {
                    FAText(
                        text = title,
                        contentPadding = PaddingValues(
                            bottom = Providers.spacing.xxs,
                        ),
                        maxLines = 1,
                        color = mainColor
                    )

                    FAText(
                        text = subtitle,
                        style = Providers.typography.bodyM,
                        color = variantColor,
                        contentPadding = PaddingValues(
                            top = Providers.spacing.xxs,
                        ),
                        maxLines = 1
                    )
                }
            }

            if (!trailingTitle.isNullOrEmpty()) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    if (trailingSubtitle.isNullOrEmpty()) {
                        FAText(
                            text = trailingTitle,
                            maxLines = 1,
                            color = mainColor
                        )
                    } else {
                        FAText(
                            text = trailingTitle,
                            contentPadding = PaddingValues(
                                bottom = Providers.spacing.xs,
                            ),
                            maxLines = 1,
                            color = mainColor
                        )

                        FAText(
                            text = trailingSubtitle,
                            color = variantColor,
                            contentPadding = PaddingValues(
                                top = Providers.spacing.xs,
                            ),
                            maxLines = 1
                        )
                    }
                }
            }

            trailingIcon?.let {
                Box(modifier = Modifier.padding(start = Providers.spacing.s)) {
                    it()
                }
            }
        }
    }
}