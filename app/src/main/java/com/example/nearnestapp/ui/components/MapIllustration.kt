package com.example.nearnestapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Moped
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.nearnestapp.ui.theme.NearNestPrimary

@Composable
fun MapIllustration(
    modifier: Modifier = Modifier,
    isTrackingRoute: Boolean = false
) {
    Box(
        modifier = modifier
            .background(Color(0xFFE8F5E9)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // Draw green parks & blue water blocks
            drawRect(
                color = Color(0xFFC8E6C9),
                topLeft = Offset(width * 0.1f, height * 0.15f),
                size = Size(width * 0.3f, height * 0.25f)
            )
            drawRect(
                color = Color(0xFFC8E6C9),
                topLeft = Offset(width * 0.6f, height * 0.55f),
                size = Size(width * 0.35f, height * 0.3f)
            )
            drawRect(
                color = Color(0xFFBBDEFB),
                topLeft = Offset(width * 0.65f, height * 0.1f),
                size = Size(width * 0.25f, height * 0.2f)
            )

            // Draw grid roads
            val roadColor = Color.White
            val roadStroke = 24.dp.toPx()

            // Horizontal roads
            drawLine(
                color = roadColor,
                start = Offset(0f, height * 0.35f),
                end = Offset(width, height * 0.35f),
                strokeWidth = roadStroke
            )
            drawLine(
                color = roadColor,
                start = Offset(0f, height * 0.7f),
                end = Offset(width, height * 0.7f),
                strokeWidth = roadStroke
            )

            // Vertical & diagonal roads
            drawLine(
                color = roadColor,
                start = Offset(width * 0.35f, 0f),
                end = Offset(width * 0.35f, height),
                strokeWidth = roadStroke
            )
            drawLine(
                color = roadColor,
                start = Offset(width * 0.75f, 0f),
                end = Offset(width * 0.75f, height),
                strokeWidth = roadStroke
            )

            if (isTrackingRoute) {
                // Route path line (Green)
                val path = Path().apply {
                    moveTo(width * 0.25f, height * 0.7f)
                    lineTo(width * 0.35f, height * 0.7f)
                    lineTo(width * 0.35f, height * 0.35f)
                    lineTo(width * 0.75f, height * 0.35f)
                }
                drawPath(
                    path = path,
                    color = NearNestPrimary,
                    style = Stroke(width = 10.dp.toPx())
                )
            }
        }

        if (!isTrackingRoute) {
            // Location Pin centered
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location Pin",
                tint = NearNestPrimary,
                modifier = Modifier.size(56.dp)
            )
            // Blue user pulse dot below pin
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(Color(0xFF2563EB), CircleShape)
            )
        } else {
            // Scooter at start of route
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .size(40.dp)
                    .background(NearNestPrimary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Moped,
                    contentDescription = "Delivery",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            // Destination House at end of route
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(40.dp)
                    .background(Color(0xFFEF4444), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Destination",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
