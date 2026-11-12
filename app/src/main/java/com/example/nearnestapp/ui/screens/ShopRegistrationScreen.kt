package com.example.nearnestapp.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearnestapp.model.Shop
import com.example.nearnestapp.ui.theme.DarkNavyText
import com.example.nearnestapp.ui.theme.DeepNavyPrimary
import com.example.nearnestapp.ui.theme.NearNestTextSecondary
import com.example.nearnestapp.ui.theme.TealGreenSecondary
import com.example.nearnestapp.ui.theme.WarmOrangeAccent

@Composable
fun ShopRegistrationScreen(
    ownerName: String,
    ownerEmail: String,
    ownerPhone: String,
    onShopRegistrationCompleted: (Shop) -> Unit,
    onBack: () -> Unit
) {
    var currentStep by remember { mutableIntStateOf(1) }

    // Step 1: Shop Info
    var shopName by remember { mutableStateOf("") }
    var shopCategory by remember { mutableStateOf("Grocery") }
    var shopDescription by remember { mutableStateOf("") }

    // Step 2: Address & Location
    var shopNumber by remember { mutableStateOf("Shop #4") }
    var streetLocality by remember { mutableStateOf("Main Market Road") }
    var societyName by remember { mutableStateOf("Green Valley Society") }
    var city by remember { mutableStateOf("Dhanbad") }
    var state by remember { mutableStateOf("Jharkhand") }
    var pincode by remember { mutableStateOf("826001") }

    // Step 3: Owner Details
    var contactName by remember { mutableStateOf(ownerName.ifBlank { "Ramesh Kumar" }) }
    var contactPhone by remember { mutableStateOf(ownerPhone.ifBlank { "+91 9876543210" }) }
    var contactEmail by remember { mutableStateOf(ownerEmail.ifBlank { "seller1@nearnest.local" }) }
    var altPhone by remember { mutableStateOf("") }

    // Step 4: Business Info
    var openTime by remember { mutableStateOf("08:00 AM") }
    var closeTime by remember { mutableStateOf("09:30 PM") }
    var workingDays by remember { mutableStateOf("Monday to Sunday") }
    var deliveryRadiusKm by remember { mutableStateOf("5.5") }
    var minOrderAmount by remember { mutableStateOf("100") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FC))
            .verticalScroll(rememberScrollState())
    ) {
        // Gradient Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(DeepNavyPrimary, Color(0xFF0F2942))
                    )
                )
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "Shop Registration",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Step $currentStep of 5: " + when (currentStep) {
                        1 -> "Shop Information"
                        2 -> "Shop Address & Location"
                        3 -> "Owner Details"
                        4 -> "Business Operating Hours"
                        else -> "Final Review & Submit"
                    },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = WarmOrangeAccent
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Step Progress Indicator
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    for (i in 1..5) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(if (i <= currentStep) WarmOrangeAccent else Color.White.copy(alpha = 0.3f))
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    when (currentStep) {
                        1 -> {
                            // Step 1: Shop Info
                            Text("Step 1: Shop Details", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(14.dp))

                            Text("Shop Name *", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedTextField(
                                value = shopName,
                                onValueChange = { shopName = it },
                                placeholder = { Text("e.g. Sharma Kirana & Supermarket") },
                                leadingIcon = { Icon(Icons.Default.Storefront, contentDescription = null, tint = NearNestTextSecondary) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Shop Category *", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedTextField(
                                value = shopCategory,
                                onValueChange = { shopCategory = it },
                                placeholder = { Text("Grocery / Food / Stationery / Bakery") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Shop Description", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedTextField(
                                value = shopDescription,
                                onValueChange = { shopDescription = it },
                                placeholder = { Text("Describe your shop products and specialty...") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        2 -> {
                            // Step 2: Address & Location
                            Text("Step 2: Shop Address & GPS Location", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(14.dp))

                            Text("Shop / House Number", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedTextField(
                                value = shopNumber,
                                onValueChange = { shopNumber = it },
                                placeholder = { Text("Shop #4") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Street / Locality", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedTextField(
                                value = streetLocality,
                                onValueChange = { streetLocality = it },
                                placeholder = { Text("Main Market Road") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Residential Society Name *", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedTextField(
                                value = societyName,
                                onValueChange = { societyName = it },
                                placeholder = { Text("Green Valley Society") },
                                leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = TealGreenSecondary) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("City", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                                    Spacer(modifier = Modifier.height(6.dp))
                                    OutlinedTextField(value = city, onValueChange = { city = it }, singleLine = true)
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("PIN Code", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                                    Spacer(modifier = Modifier.height(6.dp))
                                    OutlinedTextField(value = pincode, onValueChange = { pincode = it }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
                                }
                            }
                        }
                        3 -> {
                            // Step 3: Owner Details
                            Text("Step 3: Owner Information", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(14.dp))

                            Text("Owner Full Name", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedTextField(
                                value = contactName,
                                onValueChange = { contactName = it },
                                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = NearNestTextSecondary) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Contact Number", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedTextField(
                                value = contactPhone,
                                onValueChange = { contactPhone = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Email Address", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedTextField(
                                value = contactEmail,
                                onValueChange = { contactEmail = it },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        4 -> {
                            // Step 4: Business Info
                            Text("Step 4: Business Hours & Delivery", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(14.dp))

                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Opening Time", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                                    Spacer(modifier = Modifier.height(6.dp))
                                    OutlinedTextField(value = openTime, onValueChange = { openTime = it }, leadingIcon = { Icon(Icons.Default.Schedule, contentDescription = null, tint = NearNestTextSecondary) }, singleLine = true)
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Closing Time", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                                    Spacer(modifier = Modifier.height(6.dp))
                                    OutlinedTextField(value = closeTime, onValueChange = { closeTime = it }, leadingIcon = { Icon(Icons.Default.Schedule, contentDescription = null, tint = NearNestTextSecondary) }, singleLine = true)
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Working Days", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedTextField(value = workingDays, onValueChange = { workingDays = it }, singleLine = true, modifier = Modifier.fillMaxWidth())

                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Delivery Service Radius (km)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(6.dp))
                            OutlinedTextField(value = deliveryRadiusKm, onValueChange = { deliveryRadiusKm = it }, singleLine = true, modifier = Modifier.fillMaxWidth())
                        }
                        else -> {
                            // Step 5: Final Review & Submit
                            Text("Step 5: Review & Confirm Shop Registration", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                            Spacer(modifier = Modifier.height(14.dp))

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9))
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("🏪 ${shopName.ifBlank { "My Local Shop" }}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DarkNavyText)
                                    Text("Category: $shopCategory", fontSize = 13.sp, color = NearNestTextSecondary)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("📍 $shopNumber, $streetLocality, $societyName, $city, $state - $pincode", fontSize = 13.sp, color = NearNestTextSecondary)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("👤 Owner: $contactName ($contactPhone)", fontSize = 13.sp, color = NearNestTextSecondary)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("⏰ Hours: $openTime - $closeTime ($workingDays)", fontSize = 13.sp, color = NearNestTextSecondary)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("🚚 Delivery Radius: $deliveryRadiusKm km", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TealGreenSecondary)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            TextButton(onClick = { currentStep = 1 }) {
                                Text("Edit Shop Details", color = DeepNavyPrimary, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    HorizontalDivider(color = Color(0xFFF1F5F9))
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (currentStep > 1) {
                            OutlinedButton(
                                onClick = { currentStep-- },
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Back", color = DarkNavyText)
                            }
                        } else {
                            Spacer(modifier = Modifier.width(1.dp))
                        }

                        Button(
                            onClick = {
                                if (currentStep < 5) {
                                    currentStep++
                                } else {
                                    val newShop = Shop(
                                        id = "shop_${System.currentTimeMillis()}",
                                        name = shopName.ifBlank { "Sharma Kirana & Supermarket" },
                                        category = shopCategory,
                                        description = shopDescription.ifBlank { "Quality products sourced locally in your neighbourhood." },
                                        imageUrl = "https://images.unsplash.com/photo-1578916171728-46686eac8d58?auto=format&fit=crop&q=80&w=400",
                                        distanceKm = 0.8,
                                        rating = 5.0,
                                        reviewsCount = "New Verified Store",
                                        isOpen = true,
                                        openTime = openTime,
                                        closeTime = closeTime,
                                        address = "$shopNumber, $streetLocality, $societyName",
                                        shopNumber = shopNumber,
                                        streetLocality = streetLocality,
                                        society = societyName,
                                        city = city,
                                        state = state,
                                        pincode = pincode,
                                        deliveryRadiusKm = deliveryRadiusKm.toDoubleOrNull() ?: 5.5,
                                        ownerName = contactName,
                                        ownerPhone = contactPhone,
                                        workingDays = workingDays,
                                        minOrderAmount = minOrderAmount.toIntOrNull() ?: 100,
                                        verificationStatus = "Approved"
                                    )
                                    onShopRegistrationCompleted(newShop)
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = WarmOrangeAccent)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = if (currentStep == 5) "Submit Shop Registration" else "Next Step",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(
                                    imageVector = if (currentStep == 5) Icons.Default.CheckCircle else Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
