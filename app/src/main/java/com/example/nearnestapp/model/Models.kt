package com.example.nearnestapp.model

data class Shop(
    val id: String,
    val name: String,
    val category: String,
    val description: String = "Quality products sourced locally in your neighbourhood.",
    val imageUrl: String,
    val distanceKm: Double,
    val rating: Double,
    val reviewsCount: String,
    val isOpen: Boolean = true,
    val openTime: String = "08:00 AM",
    val closeTime: String = "10:00 PM",
    val address: String,
    val shopNumber: String = "Shop #4",
    val streetLocality: String = "Main Market Road",
    val society: String = "Green Valley Society",
    val city: String = "Dhanbad",
    val state: String = "Jharkhand",
    val pincode: String = "826001",
    val deliveryRadiusKm: Double = 6.0,
    val ownerName: String = "Ramesh Kumar",
    val ownerPhone: String = "+91 9876543210",
    val altContactPhone: String = "",
    val workingDays: String = "Monday to Sunday",
    val minOrderAmount: Int = 100,
    val isDeliveryAvailable: Boolean = true,
    val verificationStatus: String = "Approved",
    val latitude: Double = 23.7957,
    val longitude: Double = 86.4304
)

data class Product(
    val id: String,
    val shopId: String = "shop_1",
    val name: String,
    val price: Int,
    val originalPrice: Int? = null,
    val discountPercent: String? = null,
    val rating: Double,
    val reviewsCount: String,
    val distance: String,
    val imageUrl: String,
    val unit: String = "1 Pack",
    val description: String = "Fresh & healthy product sourced nearby.",
    val category: String = "Grocery",
    val isAvailable: Boolean = true,
    val stockQuantity: Int = 50
)

data class CategoryItem(
    val id: String,
    val name: String,
    val colorHex: Long
)

data class SharePost(
    val id: String,
    val title: String,
    val description: String,
    val distance: String,
    val user: String,
    val imageUrl: String
)

data class CartItem(
    val product: Product,
    var quantity: Int
)

data class OrderStep(
    val id: Int,
    val title: String,
    val time: String,
    val isCompleted: Boolean,
    val isActive: Boolean
)

data class OrderRecord(
    val orderId: String,
    val customerId: String = "cust_1",
    val shopId: String,
    val shopName: String,
    val customerName: String,
    val customerPhone: String,
    val deliveryAddress: String,
    val items: List<CartItem>,
    val subtotal: Int,
    val deliveryFee: Int,
    val total: Int,
    var status: String = "Order Placed", // Order Placed, Seller Accepted, Preparing Order, Ready for Pickup, Out for Delivery, Delivered, Cancelled
    val timestamp: String = "Just Now",
    val deliveryInstructions: String = "Leave at door",
    var rejectionReason: String? = null
)

data class UserProfile(
    val id: String = "user_1",
    val name: String = "Badal Prasad",
    val email: String = "customer@nearnest.local",
    val phone: String = "+91 9876543210",
    val role: String = "CUSTOMER", // CUSTOMER or SELLER
    val currentMode: String = "BUYER", // BUYER or SELLER
    val hasCompletedShopRegistration: Boolean = true,
    val shopId: String? = "shop_1",
    val walletBalance: Int = 250,
    val selectedLocation: String = "Dhanbad, Jharkhand",
    val savedAddresses: List<String> = listOf("Flat 402, Green Valley Society, Dhanbad"),
    val latitude: Double = 23.7957,
    val longitude: Double = 86.4304
)

data class SellerStats(
    val totalOrders: Int = 18,
    val newOrders: Int = 3,
    val acceptedOrders: Int = 4,
    val completedOrders: Int = 11,
    val cancelledOrders: Int = 0,
    val totalSales: Int = 4850,
    val availableProducts: Int = 14,
    val lowStockProducts: Int = 2,
    val dailySales: Int = 1150,
    val weeklySales: Int = 4850,
    val monthlySales: Int = 18400
)

data class CampusGroup(
    val id: String,
    val title: String,
    val description: String,
    val actionText: String,
    val colorHex: Long,
    val iconType: String
)

data class UrgentAlert(
    val id: String,
    val title: String,
    val description: String,
    val roomOrBlock: String,
    val postedBy: String,
    val timestamp: String
)
