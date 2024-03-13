package kr.loner.willog.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


private val SansSerifStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
)

internal val Typography = WillogTypography(
    title = SansSerifStyle.copy(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Bold
    ),
    body =  SansSerifStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = Gray
    ),
    input = SansSerifStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = Color.Black
    )
)
data class WillogTypography(
    val title:TextStyle,
    val body:TextStyle,
    val input:TextStyle
)

val LocalTypography = staticCompositionLocalOf {
    WillogTypography(
        title = SansSerifStyle,
        body = SansSerifStyle,
        input = SansSerifStyle
    )
}