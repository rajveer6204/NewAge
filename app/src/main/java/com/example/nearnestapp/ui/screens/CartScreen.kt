package com.example.nearnestapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearnestapp.model.CartItem
import com.example.nearnestapp.navigation.NavRoutes
import com.example.nearnestapp.ui.components.NearNestBottomBar
import com.example.nearnestapp.ui.components.ProductImageDisplay
import com.example.nearnestapp.ui.theme.NearNestPrimary
import com.example.nearnestapp.ui.theme.NearNestTextPrimary
import com.example.nearnestapp.ui.theme.NearNestTextSecondary

@Composable
fun CartScreen(
    cartItems: List<CartItem>,
    onUpdateQuantity: (String, Int) -> Unit,
    onCheckout: () -> Unit,
    onBack: () -> Unit,
    onNavigateTab: (String) -> Unit
) {
    val subtotal = cartItems.sumOf { it.product.price * it.quantity }
    val deliveryFee = if (cartItems.isNotEmpty()) 10 else 0
    val total = subtotal + deliveryFee

    Scaffold(
        bottomBar = {
            NearNestBottomBar(
                currentRoute = NavRoutes.CART,
                onTabSelected = onNavigateTab
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            // Top Header Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = NearNestTextPrimary
                    )
                }

                Text(
                    text = "My Cart",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = NearNestTextPrimary
                )

                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Clear Cart",
                        tint = NearNestTextSecondary
                    )
                }
            }

            // Cart Items List
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(cartItems) { item ->
                    CartItemCard(
                        item = item,
                        onUpdateQuantity = { delta -> onUpdateQuantity(item.product.id, delta) }
                    )
                }
            }

            // Price Breakdown Card & Checkout
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Subtotal", fontSize = 14.sp, color = NearNestTextSecondary)
                    Text(text = "₹$subtotal", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = NearNestTextPrimary)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Delivery Fee", fontSize = 14.sp, color = NearNestTextSecondary)
                    Text(text = "₹$deliveryFee", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = NearNestTextPrimary)
                }

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = Color(0xFFE5E7EB))
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = NearNestTextPrimary)
                    Text(text = "₹$total", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = NearNestTextPrimary)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onCheckout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NearNestPrimary)
                ) {
                    Text(
                        text = "Proceed to Checkout",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemCard(
    item: CartItem,
    onUpdateQuantity: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                ProductImageDisplay(
                    imageUrl = item.product.imageUrl,
                    productName = item.product.name,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.product.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = NearNestTextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "₹${item.product.price}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = NearNestTextPrimary
                )
            }

            // Quantity controls `- 1 +`
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .clickable { onUpdateQuantity(-1) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Decrease",
                        tint = NearNestTextPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                }

                Text(
                    text = "${item.quantity}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = NearNestTextPrimary,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .clickable { onUpdateQuantity(1) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase",
                        tint = NearNestTextPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}
