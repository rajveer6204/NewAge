package com.example.nearnestapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nearnestapp.data.MockData
import com.example.nearnestapp.model.CartItem
import com.example.nearnestapp.model.OrderRecord
import com.example.nearnestapp.model.Product
import com.example.nearnestapp.model.SellerStats
import com.example.nearnestapp.model.Shop
import com.example.nearnestapp.model.UserProfile
import com.example.nearnestapp.navigation.NavRoutes
import com.example.nearnestapp.ui.components.NearNestBottomBar
import com.example.nearnestapp.ui.components.ProductImageDisplay
import com.example.nearnestapp.ui.theme.NearNestPrimary
import com.example.nearnestapp.ui.theme.NearNestRatingYellow
import com.example.nearnestapp.ui.theme.NearNestTextPrimary
import com.example.nearnestapp.ui.theme.NearNestTextSecondary

@Composable
fun HomeScreen(
    userProfile: UserProfile = MockData.userProfile,
    shops: List<Shop> = MockData.shops,
    products: List<Product> = MockData.products,
    cartItems: List<CartItem> = emptyList(),
    sellerStats: SellerStats = MockData.sellerStats,
    sellerOrders: List<OrderRecord> = MockData.sellerOrders,
    onToggleUserMode: () -> Unit = {},
    onToggleShopOpen: () -> Unit = {},
    onShopClick: (String) -> Unit = {},
    onProductClick: (String) -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onAddToCart: (Product) -> Unit = {},
    onViewCartClick: () -> Unit = {},
    onNavigateTab: (String) -> Unit = {},
    onUpdateOrderStatus: (String, String, String?) -> Unit = { _, _, _ -> },
    onAddNewProduct: (String, Int, String, String, String) -> Unit = { _, _, _, _, _ -> },
    onDeleteProduct: (String) -> Unit = {},
    onToggleProductAvailability: (String) -> Unit = {}
) {
    var showMenuDialog by remember { mutableStateOf(false) }
    val totalCartCount = cartItems.sumOf { it.quantity }

    // If User is in Seller Mode, display Seller Dashboard!
    if (userProfile.currentMode == "SELLER") {
        val currentShop = shops.firstOrNull { it.id == userProfile.shopId } ?: shops.first()
        SellerDashboardScreen(
            userProfile = userProfile,
            shop = currentShop,
            sellerStats = sellerStats,
            products = products,
            orders = sellerOrders,
            onToggleMode = onToggleUserMode,
            onToggleShopOpen = onToggleShopOpen,
            onUpdateOrderStatus = onUpdateOrderStatus,
            onAddNewProduct = onAddNewProduct,
            onDeleteProduct = onDeleteProduct,
            onToggleProductAvailability = onToggleProductAvailability
        )
        return
    }

    if (showMenuDialog) {
        AlertDialog(
            onDismissRequest = { showMenuDialog = false },
            title = { Text("NearNest Navigation", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
            text = {
                Column {
                    Text("📍 Location: ${userProfile.selectedLocation}", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = NearNestPrimary)
                    Text("📍 Service Area: Registered shops within 5.5 km", fontSize = 12.sp, color = NearNestTextSecondary)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("👤 Active User: ${userProfile.name} (${userProfile.role})", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("🔄 Switch Mode to Seller / Shopkeeper Dashboard", fontSize = 13.sp, color = NearNestPrimary, fontWeight = FontWeight.Bold)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showMenuDialog = false
                        onToggleUserMode()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = NearNestPrimary)
                ) {
                    Text("Switch Mode", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showMenuDialog = false }) {
                    Text("Close", color = NearNestTextSecondary)
                }
            }
        )
    }

    Scaffold(
        bottomBar = {
            NearNestBottomBar(
                currentRoute = NavRoutes.HOME,
                onTabSelected = onNavigateTab
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Header Bar (Greeting + Cart Badge & Mode Switcher)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { showMenuDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = NearNestTextPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text(
                            text = "Hello, ${userProfile.name.split(" ").firstOrNull() ?: "Badal"} 👋",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = NearNestTextPrimary
                        )
                        Text(
                            text = "What do you need today?",
                            fontSize = 13.sp,
                            color = NearNestTextSecondary
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Cart Badge Button
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color(0xFFF1F5F9))
                            .clickable { onViewCartClick() }
                            .padding(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.ShoppingBag,
                                contentDescription = "Cart",
                                tint = NearNestPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                            if (totalCartCount > 0) {
                                Spacer(modifier = Modifier.width(4.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(Color(0xFFDC2626))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "$totalCartCount",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Mode Switcher Pill
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFFDCFCE7))
                            .clickable { onToggleUserMode() }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "🏪 Seller Mode",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = NearNestPrimary
                        )
                    }
                }
            }

            // Search Bar Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onNavigateToSearch() }
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    enabled = false,
                    placeholder = { Text("Search nearby shops & products...", color = NearNestTextSecondary) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = NearNestTextSecondary
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledBorderColor = Color(0xFFF3F4F6),
                        disabledContainerColor = Color(0xFFF9FAFB)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Promotional Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFF15803D), NearNestPrimary)
                        )
                    )
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Get 20% OFF\non Essentials",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            lineHeight = 24.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .clickable { onNavigateToSearch() }
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Shop Now",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = NearNestPrimary
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = NearNestPrimary,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingBag,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Registered Local Shops Section (within 5-6 km)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Nearby Registered Shops",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = NearNestTextPrimary
                    )
                    Text(
                        text = "Local stores within 5.5 km of your location",
                        fontSize = 12.sp,
                        color = NearNestTextSecondary
                    )
                }

                TextButton(onClick = onNavigateToSearch) {
                    Text(
                        text = "See All >",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = NearNestPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Local Shops Horizontal Carousel
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(shops) { shop ->
                    ShopCardItem(
                        shop = shop,
                        onClick = { onShopClick(shop.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Category Horizontal Row
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val catIcons = listOf(
                    Triple("Grocery", Icons.Default.ShoppingBag, Color(0xFFFFECE0)),
                    Triple("Snacks", Icons.Default.Fastfood, Color(0xFFFFEBEB)),
                    Triple("Drinks", Icons.Default.LocalDrink, Color(0xFFE0F2FE)),
                    Triple("More", Icons.Default.MoreHoriz, Color(0xFFDCFCE7))
                )

                items(catIcons) { cat ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { onNavigateToSearch() }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(cat.third),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = cat.second,
                                contentDescription = cat.first,
                                tint = NearNestPrimary,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = cat.first,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = NearNestTextPrimary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Nearby Deals Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nearby Deals",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = NearNestTextPrimary
                )

                TextButton(onClick = onNavigateToSearch) {
                    Text(
                        text = "See All >",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = NearNestPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Nearby Deals Products Horizontal Scroll
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(products) { product ->
                    HomeProductCard(
                        product = product,
                        onProductClick = { onProductClick(product.id) },
                        onAddToCart = { onAddToCart(product) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ShopCardItem(
    shop: Shop,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
            ) {
                AsyncImage(
                    model = shop.imageUrl,
                    contentDescription = shop.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (shop.isOpen) Color(0xFF166534) else Color(0xFF991B1B))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = if (shop.isOpen) "OPEN" else "CLOSED",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = shop.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = NearNestTextPrimary,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = NearNestPrimary,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${shop.distanceKm} km • ${shop.category}",
                        fontSize = 11.sp,
                        color = NearNestTextSecondary
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = NearNestRatingYellow,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${shop.rating} (${shop.reviewsCount})",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = NearNestTextPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun HomeProductCard(
    product: Product,
    onProductClick: () -> Unit,
    onAddToCart: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(130.dp)
            .clickable { onProductClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                ProductImageDisplay(
                    imageUrl = product.imageUrl,
                    productName = product.name,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = NearNestTextPrimary,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "₹${product.price}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = NearNestTextPrimary
                )

                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(NearNestPrimary)
                        .clickable { onAddToCart() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
