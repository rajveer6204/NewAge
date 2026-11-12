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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearnestapp.model.Product
import com.example.nearnestapp.ui.components.ProductImageDisplay
import com.example.nearnestapp.ui.theme.NearNestDiscountRed
import com.example.nearnestapp.ui.theme.NearNestPrimary
import com.example.nearnestapp.ui.theme.NearNestRatingYellow
import com.example.nearnestapp.ui.theme.NearNestTextPrimary
import com.example.nearnestapp.ui.theme.NearNestTextSecondary

@Composable
fun ProductDetailsScreen(
    product: Product,
    isWishlisted: Boolean,
    onToggleWishlist: () -> Unit,
    onAddToCart: (Int) -> Unit,
    onBack: () -> Unit
) {
    var quantity by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
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

            IconButton(onClick = onToggleWishlist) {
                Icon(
                    imageVector = if (isWishlisted) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Wishlist",
                    tint = if (isWishlisted) NearNestDiscountRed else NearNestTextSecondary
                )
            }
        }

        // Product Image Container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .padding(horizontal = 24.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFF9FAFB)),
            contentAlignment = Alignment.Center
        ) {
            ProductImageDisplay(
                imageUrl = product.imageUrl,
                productName = product.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Product Details Info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = product.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = NearNestTextPrimary
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Pricing Row
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "₹${product.price}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = NearNestPrimary
                )

                if (product.originalPrice != null) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "₹${product.originalPrice}",
                        fontSize = 16.sp,
                        color = NearNestTextSecondary,
                        textDecoration = TextDecoration.LineThrough
                    )
                }

                if (product.discountPercent != null) {
                    Spacer(modifier = Modifier.width(12.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(NearNestPrimary)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = product.discountPercent,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Rating Row
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = NearNestRatingYellow,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${product.rating}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = NearNestTextPrimary
                )
                Text(
                    text = " (${product.reviewsCount})",
                    fontSize = 14.sp,
                    color = NearNestTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = product.description,
                fontSize = 14.sp,
                color = NearNestTextSecondary,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Quantity Selector Pill
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF3F4F6))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { if (quantity > 1) quantity-- },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Decrease",
                        tint = NearNestTextPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }

                Text(
                    text = "$quantity",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = NearNestTextPrimary,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { quantity++ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase",
                        tint = NearNestTextPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Add to Cart Action Button
            Button(
                onClick = { onAddToCart(quantity) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NearNestPrimary)
            ) {
                Text(
                    text = "Add to Cart",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
