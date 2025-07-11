package quo.yandex.financialawareness.presentation.ui.components

import android.graphics.Color
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun FAShimmerListItem(
    modifier: Modifier = Modifier,
    title: String? = null,
    subtitle: String? = null,
    trailingTitle: String? = null,
    trailingSubtitle: String? = null,
    showLeadingIcon: Boolean = false,
    showSubtitle: Boolean = false,
    showTrailingTitle: Boolean = false,
    showTrailingSubtitle: Boolean = false,
    showTrailingIcon: Boolean = false,
    titleHeight: Dp = Providers.spacing.l,
    subtitleHeight: Dp = Providers.spacing.m,
    backgroundColor: Color = Providers.color.surface,
    contentPadding: PaddingValues = PaddingValues(horizontal = Providers.spacing.m),
    height: Dp = Providers.spacing.xxxl
) {
    val shimmerBrush = rememberShimmerBrush()

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
            if (showLeadingIcon) {
                Box(modifier = Modifier.padding(end = Providers.spacing.m)) {
                    Box(
                        modifier = Modifier
                            .size(Providers.componentSize.iconMedium)
                            .clip(CircleShape)
                            .background(shimmerBrush)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                if (!showSubtitle) {
                    if (title != null) {
                        MTText(
                            text = title,
                            maxLines = 1
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .height(titleHeight)
                                .fillMaxWidth(0.7f)
                                .clip(Providers.shape.xs)
                                .background(shimmerBrush)
                        )
                    }
                } else {
                    if (title != null) {
                        MTText(
                            text = title,
                            contentPadding = PaddingValues(
                                bottom = Providers.spacing.xxs,
                            ),
                            maxLines = 1
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .padding(
                                    PaddingValues(
                                        bottom = Providers.spacing.xxs,
                                    )
                                )
                                .height(titleHeight)
                                .fillMaxWidth(0.7f)
                                .clip(Providers.shape.xs)
                                .background(shimmerBrush)
                        )
                    }

                    if (subtitle != null) {
                        MTText(
                            text = subtitle,
                            style = Providers.typography.bodyM,
                            color = Providers.color.onSurfaceVariant,
                            contentPadding = PaddingValues(
                                top = Providers.spacing.xxs,
                            ),
                            maxLines = 1
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .padding(
                                    PaddingValues(
                                        top = Providers.spacing.xxs,
                                    )
                                )
                                .height(subtitleHeight)
                                .fillMaxWidth(0.4f)
                                .clip(Providers.shape.xs)
                                .background(shimmerBrush)
                        )
                    }
                }
            }

            if (showTrailingTitle) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    if (!showTrailingSubtitle) {
                        if (trailingTitle != null) {
                            MTText(
                                text = trailingTitle,
                                maxLines = 1
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .height(titleHeight)
                                    .width(60.dp)
                                    .clip(Providers.shape.xs)
                                    .background(shimmerBrush)
                            )
                        }
                    } else {
                        if (trailingTitle != null) {
                            MTText(
                                text = trailingTitle,
                                contentPadding = PaddingValues(
                                    bottom = Providers.spacing.xs,
                                ),
                                maxLines = 1
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .padding(
                                        PaddingValues(
                                            bottom = Providers.spacing.xs,
                                        )
                                    )
                                    .height(titleHeight)
                                    .width(60.dp)
                                    .clip(Providers.shape.xs)
                                    .background(shimmerBrush)
                            )
                        }

                        if (trailingSubtitle != null) {
                            MTText(
                                text = trailingSubtitle,
                                color = Providers.color.onSurfaceVariant,
                                contentPadding = PaddingValues(
                                    top = Providers.spacing.xs,
                                ),
                                maxLines = 1
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .padding(
                                        PaddingValues(
                                            top = Providers.spacing.xs,
                                        )
                                    )
                                    .height(titleHeight)
                                    .width(40.dp)
                                    .clip(Providers.shape.xs)
                                    .background(shimmerBrush)
                            )
                        }
                    }
                }
            }

            if (showTrailingIcon) {
                Box(modifier = Modifier.padding(start = Providers.spacing.s)) {
                    Box(
                        modifier = Modifier
                            .size(Providers.componentSize.iconMedium)
                            .clip(CircleShape)
                            .background(shimmerBrush)
                    )
                }
            }
        }
    }
}

/**
 * Создает анимированную кисть для эффекта shimmer.
 *
 * @param shimmerColor Цвет shimmer эффекта
 * @return Анимированная кисть с градиентом
 */

@Composable
fun rememberShimmerBrush(
    shimmerColor: Color = Providers.color.inverseSurface.copy(alpha = 0.1f)
): Brush {
    val transition = rememberInfiniteTransition(label = "ShimmerTransition")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200),
        ),
        label = "ShimmerAnimation"
    )

    return Brush.linearGradient(
        colors = listOf(
            shimmerColor.copy(alpha = 0.6f),
            shimmerColor.copy(alpha = 0.2f),
            shimmerColor.copy(alpha = 0.6f),
        ),
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
}