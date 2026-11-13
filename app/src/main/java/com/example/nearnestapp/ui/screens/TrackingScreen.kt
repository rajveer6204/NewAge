package com.example.nearnestapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.nearnestapp.ui.theme.NearNestTheme

@Composable
fun TrackingScreen(
    onBack: () -> Unit = {}
) {
    OrderTrackingScreen(
        onBack = onBack
    )
}

@Preview(showBackground = true)
@Composable
fun TrackingScreenPreview() {
    NearNestTheme {
        TrackingScreen()
    }
}
