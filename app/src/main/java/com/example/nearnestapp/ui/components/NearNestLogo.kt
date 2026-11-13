package com.example.nearnestapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.nearnestapp.ui.theme.NearNestPrimary

@Composable
fun NearNestLogo(
    size: Dp = 100.dp,
    backgroundColor: Color = NearNestPrimary,
    iconColor: Color = Color.White
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(size * 0.28f))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        // Outer house silhouette
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = "NearNest Logo",
            tint = iconColor,
            modifier = Modifier.size(size * 0.65f)
        )
        // Inner location pin
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            tint = backgroundColor,
            modifier = Modifier.size(size * 0.35f)
        )
    }
}
