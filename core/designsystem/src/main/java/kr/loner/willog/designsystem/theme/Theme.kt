package kr.loner.willog.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalDarkTheme = compositionLocalOf { true }
private val DarkColorScheme = darkColorScheme(
    primary = White,
    onPrimary = Gray,
    primaryContainer = Graphite,
)

private val LightColorScheme = lightColorScheme(
    primary = Black,
    onPrimary = Gray,
    primaryContainer = White,
)

@Composable
fun WillogTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    CompositionLocalProvider(
        LocalDarkTheme provides darkTheme,
        LocalTypography provides Typography
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

object WillogTheme {
    val typography: WillogTypography
        @Composable
        get() = LocalTypography.current
}

