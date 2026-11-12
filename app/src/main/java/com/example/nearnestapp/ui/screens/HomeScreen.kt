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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearnestapp.data.MockData
import com.example.nearnestapp.model.Product
import com.example.nearnestapp.navigation.NavRoutes
import com.example.nearnestapp.ui.components.CampusGroupsSection
import com.example.nearnestapp.ui.components.NearNestBottomBar
import com.example.nearnestapp.ui.components.ProductImageDisplay
import com.example.nearnestapp.ui.theme.NearNestPrimary
import com.example.nearnestapp.ui.theme.NearNestTextPrimary
import com.example.nearnestapp.ui.theme.NearNestTextSecondary

@Composable
fun HomeScreen(
    userName: String = "Badal",
    onProductClick: (String) -> Unit,
    onNavigateToSearch: () -> Unit,
    onAddToCart: (Product) -> Unit,
    onNavigateTab: (String) -> Unit
) {
    var showMenuDialog by remember { mutableStateOf(false) }
    var activeGroupDialogTitle by remember { mutableStateOf<String?>(null) }

    if (showMenuDialog) {
        AlertDialog(
            onDismissRequest = { showMenuDialog = false },
            title = { Text("NearNest Campus Menu", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
            text = {
                Column {
                    Text("📍 Location: Dhanbad, Jharkhand", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = NearNestPrimary)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("🛒 Everyday Marketplace", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text("Order notebooks, calculators, chargers directly.", fontSize = 12.sp, color = NearNestTextSecondary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("🤝 Borrow & Lend", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text("Borrow drafters, lab coats, chargers for exams.", fontSize = 12.sp, color = NearNestTextSecondary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("🏃 Student Runners", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text("Earn delivery fees by carrying food/goods.", fontSize = 12.sp, color = NearNestTextSecondary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("🚨 Urgent Broadcast", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text("Post urgent alerts right before viva/demos.", fontSize = 12.sp, color = NearNestTextSecondary)
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    showMenuDialog = false
                    onNavigateToSearch()
                }) {
                    Text("Explore Marketplace", color = NearNestPrimary, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showMenuDialog = false }) {
                    Text("Close", color = NearNestTextSecondary)
                }
            }
        )
    }

    if (activeGroupDialogTitle != null) {
        AlertDialog(
            onDismissRequest = { activeGroupDialogTitle = null },
            title = { Text(activeGroupDialogTitle ?: "", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
            text = {
                Column {
                    when (activeGroupDialogTitle) {
                        "group_marketplace" -> {
                            Text("Catalog available for immediate order from campus sellers:", fontSize = 13.sp, color = NearNestTextSecondary)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("• Spiral Notebooks (₹60)\n• Pilot V5 Pens (₹40)\n• Casio Calculator (₹650)\n• Phone Fast Charger (₹150)", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        }
                        "group_borrow" -> {
                            Text("Active items available for free exam borrow:", fontSize = 13.sp, color = NearNestTextSecondary)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("• Engineering Mini Drafter (Hostel 1)\n• 65W Laptop Type-C Charger (Block B)\n• Lab Coat Size L (Block A)", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        }
                        "group_runners" -> {
                            Text("Student Runner Delivery Network:", fontSize = 13.sp, color = NearNestTextSecondary)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("• Active Runners: 14 students online\n• Avg Delivery Time: 8 mins within campus\n• Instant delivery fee credited to wallet.", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        }
                        else -> {
                            Text("Active Urgent Broadcast Alerts:", fontSize = 13.sp, color = NearNestTextSecondary)
                            Spacer(modifier = Modifier.height(8.dp))
                            MockData.urgentAlerts.forEach { alert ->
                                Text("🚨 ${alert.title}\n${alert.roomOrBlock} • ${alert.timestamp}", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(6.dp))
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    activeGroupDialogTitle = null
                    onNavigateToSearch()
                }) {
                    Text("Open View", color = NearNestPrimary, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { activeGroupDialogTitle = null }) {
                    Text("Dismiss", color = NearNestTextSecondary)
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
            // Header Bar (Top-left 3 lines Menu icon)
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
                            contentDescription = "Menu (3 Lines)",
                            tint = NearNestTextPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Hello, $userName 👋",
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

                IconButton(
                    onClick = { showMenuDialog = true },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(0xFFF3F4F6))
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = NearNestTextPrimary
                    )
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
                    placeholder = { Text("Search nearby...", color = NearNestTextSecondary) },
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

            // Campus Building Groups Section (Everyday Marketplace, Borrow & Lend, Student Runners, Urgent Broadcast)
            CampusGroupsSection(
                onGroupClick = { groupId ->
                    activeGroupDialogTitle = groupId
                }
            )

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
                items(MockData.products) { product ->
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
