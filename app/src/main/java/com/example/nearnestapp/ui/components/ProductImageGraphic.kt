package com.example.nearnestapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage

@Composable
fun ProductImageDisplay(
    imageUrl: String,
    productName: String,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = productName,
        modifier = modifier,
        loading = {
            ProductFallbackGraphic(productName = productName)
        },
        error = {
            ProductFallbackGraphic(productName = productName)
        }
    )
}

@Composable
fun ProductFallbackGraphic(
    productName: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF3F4F6)),
        contentAlignment = Alignment.Center
    ) {
        when {
            productName.contains("Milk", ignoreCase = true) -> {
                // Milk Pouch Graphic (Amul Taaza style)
                Canvas(modifier = Modifier.fillMaxSize().padding(12.dp)) {
                    val w = size.width
                    val h = size.height

                    // Blue & White Milk Pouch
                    val pouchPath = Path().apply {
                        moveTo(w * 0.25f, h * 0.15f)
                        lineTo(w * 0.75f, h * 0.15f)
                        lineTo(w * 0.85f, h * 0.85f)
                        lineTo(w * 0.15f, h * 0.85f)
                        close()
                    }

                    drawPath(pouchPath, color = Color(0xFF2563EB))

                    // White center wave
                    drawRect(
                        color = Color.White,
                        topLeft = Offset(w * 0.2f, h * 0.35f),
                        size = Size(w * 0.6f, h * 0.35f)
                    )

                    // Red Amul logo accent
                    drawCircle(
                        color = Color(0xFFDC2626),
                        radius = w * 0.08f,
                        center = Offset(w * 0.5f, h * 0.45f)
                    )
                }
                Text(
                    text = if (productName.contains("Amul", ignoreCase = true)) "Amul\nTaaza" else productName,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E3A8A),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            productName.contains("Apple", ignoreCase = true) -> {
                // Fresh Red Apple Graphic
                Canvas(modifier = Modifier.fillMaxSize().padding(12.dp)) {
                    val w = size.width
                    val h = size.height

                    // Red Apple Body
                    drawCircle(
                        color = Color(0xFFDC2626),
                        radius = w * 0.32f,
                        center = Offset(w * 0.42f, h * 0.52f)
                    )
                    drawCircle(
                        color = Color(0xFFEF4444),
                        radius = w * 0.32f,
                        center = Offset(w * 0.58f, h * 0.52f)
                    )
                    // Green Leaf
                    drawCircle(
                        color = Color(0xFF16A34A),
                        radius = w * 0.08f,
                        center = Offset(w * 0.55f, h * 0.22f)
                    )
                }
            }
            productName.contains("Bread", ignoreCase = true) -> {
                // Loaf Bread Graphic
                Canvas(modifier = Modifier.fillMaxSize().padding(12.dp)) {
                    val w = size.width
                    val h = size.height

                    drawRoundRect(
                        color = Color(0xFFD97706),
                        topLeft = Offset(w * 0.15f, h * 0.3f),
                        size = Size(w * 0.7f, h * 0.45f),
                        cornerRadius = CornerRadius(16f, 16f)
                    )
                    drawRoundRect(
                        color = Color(0xFFFBBF24),
                        topLeft = Offset(w * 0.2f, h * 0.35f),
                        size = Size(w * 0.6f, h * 0.35f),
                        cornerRadius = CornerRadius(12f, 12f)
                    )
                }
            }
            else -> {
                Text(
                    text = productName,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF374151)
                )
            }
        }
    }
}
