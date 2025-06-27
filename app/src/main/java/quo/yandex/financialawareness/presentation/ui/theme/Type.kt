package quo.yandex.financialawareness.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import quo.yandex.financialawareness.R

val RobotoRegular = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal, FontStyle.Normal),
)

val RobotoMedium = FontFamily(
    Font(R.font.roboto_medium, FontWeight.W500, FontStyle.Normal),
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = RobotoRegular,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        color = OnSurface,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = RobotoRegular,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        color = OnSurfaceVariant,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),

    titleLarge = TextStyle(
        fontFamily = RobotoRegular,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        color = OnSurface,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    labelSmall = TextStyle(
        fontFamily = RobotoMedium,
        fontWeight = FontWeight.W500,
        fontStyle = FontStyle.Normal,
        color = OnSurfaceVariant,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    labelMedium = TextStyle(
        fontFamily = RobotoMedium,
        fontWeight = FontWeight.W600,
        fontStyle = FontStyle.Normal,
        color = OnSurface,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
)