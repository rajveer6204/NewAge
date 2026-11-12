package com.example.nearnestapp.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearnestapp.navigation.NavRoutes
import com.example.nearnestapp.ui.components.NearNestBottomBar
import com.example.nearnestapp.ui.theme.NearNestPrimary
import com.example.nearnestapp.ui.theme.NearNestTextPrimary
import com.example.nearnestapp.ui.theme.NearNestTextSecondary

@Composable
fun ShareEssentialsScreen(
    onCreateSharePostClick: () -> Unit,
    onSkipClick: () -> Unit,
    onBack: () -> Unit,
    onNavigateTab: (String) -> Unit
) {
    Scaffold(
        bottomBar = {
            NearNestBottomBar(
                currentRoute = NavRoutes.SHARE,
                onTabSelected = onNavigateTab
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = NearNestTextPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Box of Essentials Illustration
            Box(
                modifier = Modifier
                    .size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height

                    // Background soft circle
                    drawCircle(
                        color = Color(0xFFF0FDF4),
                        radius = w * 0.45f
                    )

                    // Grocery Items (Milk pouch, water bottle, oil bottle) popping out of box
                    drawRoundRect(
                        color = Color(0xFF60A5FA), // Blue bottle
                        topLeft = Offset(w * 0.3f, h * 0.2f),
                        size = Size(w * 0.12f, h * 0.35f),
                        cornerRadius = CornerRadius(12f, 12f)
                    )
                    drawRoundRect(
                        color = Color(0xFF34D399), // Green bottle
                        topLeft = Offset(w * 0.58f, h * 0.18f),
                        size = Size(w * 0.14f, h * 0.38f),
                        cornerRadius = CornerRadius(14f, 14f)
                    )
                    drawRoundRect(
                        color = Color(0xFFFBBF24), // Yellow package
                        topLeft = Offset(w * 0.44f, h * 0.25f),
                        size = Size(w * 0.16f, h * 0.3f),
                        cornerRadius = CornerRadius(8f, 8f)
                    )

                    // Cardboard Sharing Box (Brown)
                    drawRoundRect(
                        color = Color(0xFFD97706),
                        topLeft = Offset(w * 0.2f, h * 0.42f),
                        size = Size(w * 0.6f, h * 0.45f),
                        cornerRadius = CornerRadius(20f, 20f)
                    )
                    // Inner box shadow/flap
                    drawRoundRect(
                        color = Color(0xFFB45309),
                        topLeft = Offset(w * 0.23f, h * 0.42f),
                        size = Size(w * 0.54f, h * 0.12f),
                        cornerRadius = CornerRadius(10f, 10f)
                    )
                }

                // Attached green share badge
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 4.dp)
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(NearNestPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "Share Essentials\nwith Your Neighbors",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = NearNestTextPrimary,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Share items like groceries,\nsnacks or daily essentials.",
                fontSize = 14.sp,
                color = NearNestTextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            // Create Share Post Button
            Button(
                onClick = onCreateSharePostClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NearNestPrimary)
            ) {
                Text(
                    text = "Create Share Post",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Skip for now text button
            TextButton(onClick = onSkipClick) {
                Text(
                    text = "Skip for now",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NearNestPrimary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
