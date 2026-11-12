package com.example.nearnestapp.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearnestapp.ui.components.NearNestLogo
import com.example.nearnestapp.ui.theme.NearNestPrimary
import com.example.nearnestapp.ui.theme.NearNestTextPrimary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Center Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NearNestLogo(size = 120.dp)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "NearNest",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = NearNestTextPrimary,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Need It. Get It Nearby.",
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium,
                color = NearNestPrimary
            )

            Spacer(modifier = Modifier.height(60.dp))

            CircularProgressIndicator(
                modifier = Modifier.size(36.dp),
                color = NearNestPrimary,
                strokeWidth = 3.dp
            )
        }

        // Green Landscape / Cityscape Illustration at Bottom
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .align(Alignment.BottomCenter)
        ) {
            val width = size.width
            val height = size.height

            // Background buildings silhouette
            drawRect(
                color = Color(0xFF86EFAC),
                topLeft = Offset(width * 0.1f, height * 0.5f),
                size = Size(width * 0.12f, height * 0.5f)
            )
            drawRect(
                color = Color(0xFF86EFAC),
                topLeft = Offset(width * 0.25f, height * 0.4f),
                size = Size(width * 0.15f, height * 0.6f)
            )
            drawRect(
                color = Color(0xFF86EFAC),
                topLeft = Offset(width * 0.45f, height * 0.55f),
                size = Size(width * 0.1f, height * 0.45f)
            )
            drawRect(
                color = Color(0xFF86EFAC),
                topLeft = Offset(width * 0.65f, height * 0.35f),
                size = Size(width * 0.18f, height * 0.65f)
            )
            drawRect(
                color = Color(0xFF86EFAC),
                topLeft = Offset(width * 0.85f, height * 0.48f),
                size = Size(width * 0.1f, height * 0.52f)
            )

            // Foreground rolling green hills wave path
            val hillPath = Path().apply {
                moveTo(0f, height * 0.6f)
                cubicTo(
                    width * 0.25f, height * 0.4f,
                    width * 0.5f, height * 0.75f,
                    width, height * 0.5f
                )
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(
                path = hillPath,
                color = NearNestPrimary
            )
        }
    }
}
