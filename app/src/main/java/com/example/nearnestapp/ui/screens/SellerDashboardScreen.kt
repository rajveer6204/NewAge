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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearnestapp.model.OrderRecord
import com.example.nearnestapp.model.Product
import com.example.nearnestapp.model.SellerStats
import com.example.nearnestapp.model.Shop
import com.example.nearnestapp.model.UserProfile
import com.example.nearnestapp.ui.components.ProductImageDisplay
import com.example.nearnestapp.ui.theme.NearNestPrimary
import com.example.nearnestapp.ui.theme.NearNestTextPrimary
import com.example.nearnestapp.ui.theme.NearNestTextSecondary

@Composable
fun SellerDashboardScreen(
    userProfile: UserProfile,
    shop: Shop,
    sellerStats: SellerStats,
    products: List<Product>,
    orders: List<OrderRecord>,
    onToggleMode: () -> Unit,
    onToggleShopOpen: () -> Unit,
    onUpdateOrderStatus: (orderId: String, newStatus: String, reason: String?) -> Unit,
    onAddNewProduct: (name: String, price: Int, category: String, unit: String, desc: String) -> Unit,
    onDeleteProduct: (String) -> Unit,
    onToggleProductAvailability: (String) -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var showAddProductDialog by remember { mutableStateOf(false) }

    var newProdName by remember { mutableStateOf("") }
    var newProdPrice by remember { mutableStateOf("") }
    var newProdCategory by remember { mutableStateOf("Grocery") }
    var newProdUnit by remember { mutableStateOf("1 Pack") }
    var newProdDesc by remember { mutableStateOf("") }

    if (showAddProductDialog) {
        AlertDialog(
            onDismissRequest = { showAddProductDialog = false },
            title = { Text("Add New Store Product", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    OutlinedTextField(
                        value = newProdName,
                        onValueChange = { newProdName = it },
                        label = { Text("Product Name") },
                        placeholder = { Text("e.g. Spiral Notebook / Fresh Milk") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = newProdPrice,
                        onValueChange = { newProdPrice = it },
                        label = { Text("Price (₹)") },
                        placeholder = { Text("60") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = newProdCategory,
                        onValueChange = { newProdCategory = it },
                        label = { Text("Category") },
                        placeholder = { Text("Grocery / Stationery / Food") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = newProdUnit,
                        onValueChange = { newProdUnit = it },
                        label = { Text("Unit / Quantity") },
                        placeholder = { Text("500ml / 1 Pack / 1 Unit") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = newProdDesc,
                        onValueChange = { newProdDesc = it },
                        label = { Text("Description") },
                        placeholder = { Text("Sourced fresh locally from shop.") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val priceInt = newProdPrice.toIntOrNull() ?: 50
                        onAddNewProduct(
                            newProdName.ifBlank { "New Product" },
                            priceInt,
                            newProdCategory,
                            newProdUnit,
                            newProdDesc.ifBlank { "Available at shop." }
                        )
                        showAddProductDialog = false
                        newProdName = ""
                        newProdPrice = ""
                        newProdDesc = ""
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = NearNestPrimary)
                ) {
                    Text("Add to Store", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddProductDialog = false }) {
                    Text("Cancel", color = NearNestTextSecondary)
                }
            }
        )
    }

    Scaffold(
        floatingActionButton = {
            if (selectedTab == 1) {
                FloatingActionButton(
                    onClick = { showAddProductDialog = true },
                    containerColor = NearNestPrimary,
                    contentColor = Color.White
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Product")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8FAFC))
                .padding(innerPadding)
        ) {
            // Top Seller Header Bar
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFDCFCE7)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Storefront,
                                    contentDescription = null,
                                    tint = NearNestPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = shop.name,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = NearNestTextPrimary
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = null,
                                        tint = NearNestTextSecondary,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(2.dp))
                                    Text(
                                        text = "${shop.society} • ${shop.deliveryRadiusKm} km Radius",
                                        fontSize = 12.sp,
                                        color = NearNestTextSecondary
                                    )
                                }
                            }
                        }

                        // Open / Closed Switch
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = if (shop.isOpen) "STORE OPEN" else "STORE CLOSED",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (shop.isOpen) NearNestPrimary else Color(0xFFDC2626)
                            )
                            Switch(
                                checked = shop.isOpen,
                                onCheckedChange = { onToggleShopOpen() },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = NearNestPrimary
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Role & Mode Switcher Pill (Customer vs Seller Mode)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF1F5F9))
                            .padding(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { onToggleMode() }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🛍️ Customer Mode",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = NearNestTextSecondary
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🏪 Seller Dashboard",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = NearNestPrimary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Metrics Summary Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MetricCard(
                    title = "Total Orders",
                    value = "${sellerStats.totalOrders}",
                    subText = "${sellerStats.newOrders} New",
                    bgColor = Color(0xFFEFF6FF),
                    textColor = Color(0xFF1E40AF),
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    title = "Total Sales",
                    value = "₹${sellerStats.totalSales}",
                    subText = "Today ₹${sellerStats.dailySales}",
                    bgColor = Color(0xFFDCFCE7),
                    textColor = Color(0xFF166534),
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    title = "Products",
                    value = "${sellerStats.availableProducts}",
                    subText = "${sellerStats.lowStockProducts} Low Stock",
                    bgColor = Color(0xFFFFFBEB),
                    textColor = Color(0xFF92400E),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tab Controls (0: Orders, 1: Inventory, 2: Store Info)
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = NearNestPrimary
                    )
                }
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Orders (${orders.size})", fontWeight = FontWeight.Bold) }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Inventory (${products.size})", fontWeight = FontWeight.Bold) }
                )
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    text = { Text("Store Hours", fontWeight = FontWeight.Bold) }
                )
            }

            // Tab Contents
            when (selectedTab) {
                0 -> SellerOrdersList(
                    orders = orders,
                    onUpdateOrderStatus = onUpdateOrderStatus
                )
                1 -> SellerInventoryList(
                    products = products,
                    onDeleteProduct = onDeleteProduct,
                    onToggleAvailability = onToggleProductAvailability
                )
                else -> SellerStoreInfoView(
                    shop = shop,
                    onToggleShopOpen = onToggleShopOpen
                )
            }
        }
    }
}

@Composable
fun MetricCard(
    title: String,
    value: String,
    subText: String,
    bgColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = textColor)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = textColor)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = subText, fontSize = 10.sp, color = textColor.copy(alpha = 0.8f))
        }
    }
}

@Composable
fun SellerOrdersList(
    orders: List<OrderRecord>,
    onUpdateOrderStatus: (orderId: String, newStatus: String, reason: String?) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(orders) { order ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = order.orderId, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = NearNestTextPrimary)
                            Text(text = "${order.customerName} • ${order.timestamp}", fontSize = 12.sp, color = NearNestTextSecondary)
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    when (order.status) {
                                        "Order Placed" -> Color(0xFFFEF3C7)
                                        "Seller Accepted", "Preparing Order" -> Color(0xFFDBEAFE)
                                        "Delivered" -> Color(0xFFDCFCE7)
                                        else -> Color(0xFFFEE2E2)
                                    }
                                )
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = order.status,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = when (order.status) {
                                    "Order Placed" -> Color(0xFF92400E)
                                    "Seller Accepted", "Preparing Order" -> Color(0xFF1E40AF)
                                    "Delivered" -> Color(0xFF166534)
                                    else -> Color(0xFF991B1B)
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "📍 ${order.deliveryAddress}", fontSize = 13.sp, color = NearNestTextSecondary)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Total Payable: ₹${order.total}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = NearNestPrimary)

                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(color = Color(0xFFF1F5F9))
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        when (order.status) {
                            "Order Placed" -> {
                                TextButton(onClick = { onUpdateOrderStatus(order.orderId, "Cancelled", "Stock unavailable") }) {
                                    Text("Reject Order", color = Color(0xFFDC2626), fontSize = 13.sp)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = { onUpdateOrderStatus(order.orderId, "Seller Accepted", null) },
                                    colors = ButtonDefaults.buttonColors(containerColor = NearNestPrimary)
                                ) {
                                    Text("Accept Order", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                            "Seller Accepted" -> {
                                Button(
                                    onClick = { onUpdateOrderStatus(order.orderId, "Preparing Order", null) },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
                                ) {
                                    Text("Start Preparing", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                            "Preparing Order" -> {
                                Button(
                                    onClick = { onUpdateOrderStatus(order.orderId, "Out for Delivery", null) },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD97706))
                                ) {
                                    Text("Dispatch / Out for Delivery", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                            "Out for Delivery" -> {
                                Button(
                                    onClick = { onUpdateOrderStatus(order.orderId, "Delivered", null) },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF059669))
                                ) {
                                    Text("Mark as Delivered", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                            else -> {
                                Text(text = "Order Completed", fontSize = 12.sp, color = NearNestTextSecondary)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SellerInventoryList(
    products: List<Product>,
    onDeleteProduct: (String) -> Unit,
    onToggleAvailability: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
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
                            .background(Color(0xFFF1F5F9)),
                        contentAlignment = Alignment.Center
                    ) {
                        ProductImageDisplay(imageUrl = product.imageUrl, productName = product.name)
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = product.name, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = NearNestTextPrimary)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = "₹${product.price} • ${product.unit}", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = NearNestPrimary)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (product.isAvailable) "Stock: ${product.stockQuantity} in stock" else "OUT OF STOCK",
                            fontSize = 11.sp,
                            color = if (product.isAvailable) Color(0xFF059669) else Color(0xFFDC2626)
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(
                            checked = product.isAvailable,
                            onCheckedChange = { onToggleAvailability(product.id) },
                            colors = SwitchDefaults.colors(checkedTrackColor = NearNestPrimary)
                        )
                        IconButton(onClick = { onDeleteProduct(product.id) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color(0xFFDC2626))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SellerStoreInfoView(
    shop: Shop,
    onToggleShopOpen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Store Configuration", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = NearNestTextPrimary)
                Spacer(modifier = Modifier.height(14.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Shop Operating Hours", fontSize = 14.sp, color = NearNestTextSecondary)
                    Text(text = "${shop.openTime} - ${shop.closeTime}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = NearNestTextPrimary)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Hyperlocal Delivery Radius", fontSize = 14.sp, color = NearNestTextSecondary)
                    Text(text = "${shop.deliveryRadiusKm} km Radius", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = NearNestPrimary)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Residential Society / Area", fontSize = 14.sp, color = NearNestTextSecondary)
                    Text(text = shop.society, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = NearNestTextPrimary)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Shop Owner", fontSize = 14.sp, color = NearNestTextSecondary)
                    Text(text = "${shop.ownerName} (${shop.ownerPhone})", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = NearNestTextPrimary)
                }
            }
        }
    }
}
