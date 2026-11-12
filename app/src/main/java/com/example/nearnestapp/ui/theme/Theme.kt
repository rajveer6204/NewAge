package com.example.nearnestapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = NearNestPrimary,
    onPrimary = NearNestBackground,
    primaryContainer = NearNestPrimaryLight,
    onPrimaryContainer = NearNestPrimaryDark,
    surface = NearNestBackground,
    onSurface = NearNestTextPrimary,
    surfaceVariant = NearNestSurfaceLight,
    onSurfaceVariant = NearNestTextSecondary,
    background = NearNestBackground,
    onBackground = NearNestTextPrimary
)

@Composable
fun NearNestTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
