package com.payam1991gr.kmp.playground.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColors = ThemeColors.Light.run {
    lightColorScheme(
        primary = primary,
        secondary = secondary,
        tertiary = tertiary,
        onPrimary = onPrimary,
        onSecondary = onSecondary,
        onTertiary = onTertiary,
        primaryContainer = primaryContainer,
        secondaryContainer = secondaryContainer,
        tertiaryContainer = tertiaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        onTertiaryContainer = onTertiaryContainer,
    )
}

private val DarkColors = ThemeColors.Dark.run {
    darkColorScheme(
        primary = primary,
        secondary = secondary,
        tertiary = tertiary,
        onPrimary = onPrimary,
        onSecondary = onSecondary,
        onTertiary = onTertiary,
        primaryContainer = primaryContainer,
        secondaryContainer = secondaryContainer,
        tertiaryContainer = tertiaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        onTertiaryContainer = onTertiaryContainer,
    )
}

@Composable
expect fun dynamicColorScheme(dynamicColor: Boolean, darkTheme: Boolean): ColorScheme?

@Composable
fun KmpPlaygroundTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
//    val colorScheme = dynamicColorScheme(dynamicColor, darkTheme) ?: when {
    val colorScheme = when {
        darkTheme -> DarkColors
        else -> LightColors
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
