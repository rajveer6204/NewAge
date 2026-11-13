package com.example.nearnestapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.nearnestapp.ui.theme.NearNestTheme

@Composable
fun ShareScreen(
    onCreateSharePostClick: () -> Unit = {},
    onSkipClick: () -> Unit = {},
    onBack: () -> Unit = {},
    onNavigateTab: (String) -> Unit = {}
) {
    ShareEssentialsScreen(
        onCreateSharePostClick = onCreateSharePostClick,
        onSkipClick = onSkipClick,
        onBack = onBack,
        onNavigateTab = onNavigateTab
    )
}

@Preview(showBackground = true)
@Composable
fun ShareScreenPreview() {
    NearNestTheme {
        ShareScreen()
    }
}
