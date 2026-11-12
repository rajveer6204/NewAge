package com.example.nearnestapp.data.repository

import com.example.nearnestapp.data.MockData
import com.example.nearnestapp.model.OrderRecord
import com.example.nearnestapp.model.Product
import com.example.nearnestapp.model.Shop
import com.example.nearnestapp.model.UserProfile
import com.example.nearnestapp.util.LocationUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class FirebaseRepository {

    private val _userProfile = MutableStateFlow(MockData.userProfile)
    val userProfileFlow: Flow<UserProfile> = _userProfile.asStateFlow()

    private val _shops = MutableStateFlow(MockData.shops)
    val shopsFlow: Flow<List<Shop>> = _shops.asStateFlow()

    private val _products = MutableStateFlow(MockData.products)
    val productsFlow: Flow<List<Product>> = _products.asStateFlow()

    private val _sellerOrders = MutableStateFlow(MockData.sellerOrders)
    val sellerOrdersFlow: Flow<List<OrderRecord>> = _sellerOrders.asStateFlow()

    fun getNearbyShopsFlow(userLat: Double, userLon: Double, maxRadiusKm: Double = 6.0): Flow<List<Shop>> {
        return _shops.map { list ->
            list.filter { shop ->
                LocationUtils.isWithinServiceRadius(
                    shopLat = shop.latitude,
                    shopLon = shop.longitude,
                    userLat = userLat,
                    userLon = userLon,
                    maxRadiusKm = maxRadiusKm
                )
            }
        }
    }

    fun registerBuyer(name: String, email: String, phone: String) {
        _userProfile.value = UserProfile(
            name = name,
            email = email,
            phone = phone,
            role = "CUSTOMER",
            currentMode = "BUYER",
            hasCompletedShopRegistration = true
        )
    }

    fun loginBuyer(email: String) {
        _userProfile.value = UserProfile(
            name = "Badal Prasad",
            email = email,
            phone = "+91 9876543210",
            role = "CUSTOMER",
            currentMode = "BUYER",
            hasCompletedShopRegistration = true
        )
    }

    fun registerSeller(name: String, email: String, phone: String) {
        _userProfile.value = UserProfile(
            name = name,
            email = email,
            phone = phone,
            role = "SELLER",
            currentMode = "SELLER",
            hasCompletedShopRegistration = false,
            shopId = null
        )
    }

    fun loginSeller(email: String) {
        val existingShop = _shops.value.firstOrNull { it.ownerName.contains(_userProfile.value.name, ignoreCase = true) }
        _userProfile.value = UserProfile(
            name = "Aarav (Shopkeeper)",
            email = email,
            phone = "+91 9812345678",
            role = "SELLER",
            currentMode = "SELLER",
            hasCompletedShopRegistration = existingShop != null,
            shopId = existingShop?.id ?: "shop_1"
        )
    }

    fun completeShopRegistration(shop: Shop) {
        val updatedShops = listOf(shop) + _shops.value
        _shops.value = updatedShops
        _userProfile.value = _userProfile.value.copy(
            hasCompletedShopRegistration = true,
            shopId = shop.id
        )
    }

    fun authenticateUser(name: String, email: String, role: String) {
        val initialMode = if (role.equals("SELLER", ignoreCase = true)) "SELLER" else "BUYER"
        val isCompleted = !role.equals("SELLER", ignoreCase = true)
        val updatedProfile = UserProfile(
            name = name,
            email = email,
            role = role,
            currentMode = initialMode,
            hasCompletedShopRegistration = isCompleted,
            shopId = if (role.equals("SELLER", ignoreCase = true)) "shop_1" else null
        )
        _userProfile.value = updatedProfile
    }

    fun addProduct(product: Product) {
        _products.value = listOf(product) + _products.value
    }

    fun updateOrderStatus(orderId: String, newStatus: String, rejectionReason: String? = null) {
        _sellerOrders.value = _sellerOrders.value.map { order ->
            if (order.orderId == orderId) {
                order.copy(status = newStatus, rejectionReason = rejectionReason)
            } else {
                order
            }
        }
    }
}
