package com.example.fmsystem.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = PinkPrimary,
    secondary = PurplePrimary,
    background = BackgroundColor,
    surface = CardColor,
    onPrimary = White,
    onSecondary = White,
    onBackground = TextDark,
    onSurface = TextDark
)

@Composable
fun FMSystemTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}