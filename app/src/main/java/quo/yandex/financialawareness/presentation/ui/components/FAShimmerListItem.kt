package quo.yandex.financialawareness.presentation.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
    titleHeight: Dp = 20.dp,
    subtitleHeight: Dp = 16.dp,
    backgroundColor: Color = colorScheme.surface,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    height: Dp = 72.dp
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
                Box(modifier = Modifier.padding(end = 16.dp)) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
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
                        Text(
                            text = title,
                            maxLines = 1
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .height(titleHeight)
                                .fillMaxWidth(0.7f)
                                .clip(RoundedCornerShape(4.dp))
                                .background(shimmerBrush)
                        )
                    }
                } else {
                    if (title != null) {
                        Text(
                            text = title,
                            modifier = Modifier.padding(
                                PaddingValues(
                                    bottom = 2.dp,
                                )
                            ),
                            maxLines = 1
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .padding(
                                    PaddingValues(
                                        bottom = 2.dp,
                                    )
                                )
                                .height(titleHeight)
                                .fillMaxWidth(0.7f)
                                .clip(RoundedCornerShape(4.dp))
                                .background(shimmerBrush)
                        )
                    }

                    if (subtitle != null) {
                        Text(
                            text = subtitle,
                            style = typography.bodyMedium,
                            color = colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(
                                PaddingValues(
                                    top = 2.dp,
                                )
                            ),
                            maxLines = 1
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .padding(
                                    PaddingValues(
                                        top = 2.dp,
                                    )
                                )
                                .height(subtitleHeight)
                                .fillMaxWidth(0.4f)
                                .clip(RoundedCornerShape(4.dp))
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
                            Text(
                                text = trailingTitle,
                                maxLines = 1
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .height(titleHeight)
                                    .width(60.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(shimmerBrush)
                            )
                        }
                    } else {
                        if (trailingTitle != null) {
                            Text(
                                text = trailingTitle,
                                modifier = Modifier.padding(
                                    PaddingValues(
                                        bottom = 4.dp,
                                    )
                                ),
                                maxLines = 1
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .padding(
                                        PaddingValues(
                                            bottom = 4.dp,
                                        )
                                    )
                                    .height(titleHeight)
                                    .width(60.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(shimmerBrush)
                            )
                        }

                        if (trailingSubtitle != null) {
                            Text(
                                modifier = Modifier.padding(
                                    PaddingValues(
                                        top = 4.dp,
                                    )
                                ),
                                text = trailingSubtitle,
                                color = colorScheme.onSurfaceVariant,
                                maxLines = 1
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .padding(
                                        PaddingValues(
                                            top = 4.dp,
                                        )
                                    )
                                    .height(titleHeight)
                                    .width(40.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(shimmerBrush)
                            )
                        }
                    }
                }
            }

            if (showTrailingIcon) {
                Box(modifier = Modifier.padding(start = 8.dp)) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
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
    shimmerColor: Color = colorScheme.inverseSurface.copy(alpha = 0.1f)
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