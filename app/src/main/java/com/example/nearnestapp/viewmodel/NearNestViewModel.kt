package com.example.nearnestapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nearnestapp.data.MockData
import com.example.nearnestapp.data.repository.FirebaseRepository
import com.example.nearnestapp.model.CartItem
import com.example.nearnestapp.model.OrderRecord
import com.example.nearnestapp.model.Product
import com.example.nearnestapp.model.SellerStats
import com.example.nearnestapp.model.SharePost
import com.example.nearnestapp.model.Shop
import com.example.nearnestapp.model.UserProfile
import com.example.nearnestapp.util.LocationUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NearNestViewModel : ViewModel() {

    private val repository = FirebaseRepository()

    private val _userProfile = MutableStateFlow(MockData.userProfile)
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _shops = MutableStateFlow(MockData.shops)
    val shops: StateFlow<List<Shop>> = _shops.asStateFlow()

    private val _products = MutableStateFlow(MockData.products)
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _sellerStats = MutableStateFlow(MockData.sellerStats)
    val sellerStats: StateFlow<SellerStats> = _sellerStats.asStateFlow()

    private val _sellerOrders = MutableStateFlow(MockData.sellerOrders)
    val sellerOrders: StateFlow<List<OrderRecord>> = _sellerOrders.asStateFlow()

    private val _cartItems = MutableStateFlow<List<CartItem>>(
        listOf(
            CartItem(MockData.products.first { it.id == "amul_milk" }, 1),
            CartItem(MockData.products.first { it.id == "bread" }, 1)
        )
    )
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    private val _searchQuery = MutableStateFlow("Milk")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _wishlist = MutableStateFlow<Set<String>>(setOf("amul_milk"))
    val wishlist: StateFlow<Set<String>> = _wishlist.asStateFlow()

    private val _sharePosts = MutableStateFlow(MockData.sharePosts)
    val sharePosts: StateFlow<List<SharePost>> = _sharePosts.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    private val _cartConflictProduct = MutableStateFlow<Product?>(null)
    val cartConflictProduct: StateFlow<Product?> = _cartConflictProduct.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }

    fun updateLocation(locationName: String, lat: Double = 23.7957, lon: Double = 86.4304) {
        _userProfile.update {
            it.copy(selectedLocation = locationName, latitude = lat, longitude = lon)
        }
        recalculateNearbyShops(lat, lon)
    }

    private fun recalculateNearbyShops(userLat: Double, userLon: Double) {
        _shops.update { currentList ->
            currentList.map { shop ->
                val dist = LocationUtils.calculateDistanceKm(userLat, userLon, shop.latitude, shop.longitude)
                shop.copy(distanceKm = dist)
            }.filter { it.distanceKm <= 6.0 }
        }
    }

    fun updateUserProfile(name: String, email: String, role: String) {
        val initialMode = if (role.equals("SELLER", ignoreCase = true)) "SELLER" else "BUYER"
        _userProfile.update {
            it.copy(name = name, email = email, role = role, currentMode = initialMode)
        }
        repository.authenticateUser(name, email, role)
    }

    fun toggleUserMode() {
        _userProfile.update {
            val newMode = if (it.currentMode == "BUYER") "SELLER" else "BUYER"
            it.copy(currentMode = newMode)
        }
    }

    fun toggleShopOpen(shopId: String) {
        _shops.update { current ->
            current.map { shop ->
                if (shop.id == shopId) shop.copy(isOpen = !shop.isOpen) else shop
            }
        }
    }

    fun updateOrderStatus(orderId: String, newStatus: String, reason: String? = null) {
        _sellerOrders.update { current ->
            current.map { order ->
                if (order.orderId == orderId) {
                    order.copy(status = newStatus, rejectionReason = reason)
                } else {
                    order
                }
            }
        }
        repository.updateOrderStatus(orderId, newStatus, reason)
    }

    fun addNewProduct(name: String, price: Int, category: String, unit: String, description: String) {
        val newProd = Product(
            id = "prod_${System.currentTimeMillis()}",
            shopId = _userProfile.value.shopId ?: "shop_1",
            name = name,
            price = price,
            rating = 5.0,
            reviewsCount = "New",
            distance = "Your Store (Same Building)",
            imageUrl = "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?auto=format&fit=crop&q=80&w=400",
            unit = unit,
            description = description,
            category = category
        )
        _products.update { listOf(newProd) + it }
        _sellerStats.update { it.copy(availableProducts = it.availableProducts + 1) }
        repository.addProduct(newProd)
    }

    fun deleteProduct(productId: String) {
        _products.update { current -> current.filterNot { it.id == productId } }
        _sellerStats.update { it.copy(availableProducts = (it.availableProducts - 1).coerceAtLeast(0)) }
    }

    fun toggleProductAvailability(productId: String) {
        _products.update { current ->
            current.map { prod ->
                if (prod.id == productId) prod.copy(isAvailable = !prod.isAvailable) else prod
            }
        }
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = null
    }

    fun clearCartConflict() {
        _cartConflictProduct.value = null
    }

    fun getProductQuantityInCart(productId: String): Int {
        return _cartItems.value.find { it.product.id == productId }?.quantity ?: 0
    }

    fun getCartTotalCount(): Int {
        return _cartItems.value.sumOf { it.quantity }
    }

    fun getCartTotalSum(): Int {
        val subtotal = _cartItems.value.sumOf { it.product.price * it.quantity }
        val deliveryFee = if (_cartItems.value.isNotEmpty()) 10 else 0
        return subtotal + deliveryFee
    }

    fun addToCart(product: Product) {
        if (!product.isAvailable) {
            _snackbarMessage.value = "Sorry, ${product.name} is currently out of stock!"
            return
        }

        // Multi-Shop Cart Guard: Check if cart contains items from a different shop
        val existingShopId = _cartItems.value.firstOrNull()?.product?.shopId
        if (existingShopId != null && existingShopId != product.shopId && _cartItems.value.isNotEmpty()) {
            _cartConflictProduct.value = product
            return
        }

        performAddToCart(product)
    }

    fun confirmClearCartAndAdd(product: Product) {
        _cartItems.value = emptyList()
        _cartConflictProduct.value = null
        performAddToCart(product)
    }

    private fun performAddToCart(product: Product) {
        _cartItems.update { current ->
            val existing = current.find { it.product.id == product.id }
            if (existing != null) {
                current.map {
                    if (it.product.id == product.id) it.copy(quantity = it.quantity + 1)
                    else it
                }
            } else {
                current + CartItem(product, 1)
            }
        }
        _snackbarMessage.value = "✓ ${product.name} added to cart successfully!"
    }

    fun updateQuantity(productId: String, delta: Int) {
        _cartItems.update { current ->
            current.mapNotNull { item ->
                if (item.product.id == productId) {
                    val newQty = item.quantity + delta
                    if (newQty > 0) item.copy(quantity = newQty) else null
                } else {
                    item
                }
            }
        }
    }

    fun toggleWishlist(productId: String) {
        _wishlist.update { current ->
            if (current.contains(productId)) current - productId
            else current + productId
        }
    }

    fun addSharePost(title: String, description: String) {
        val newPost = SharePost(
            id = "share_${System.currentTimeMillis()}",
            title = title,
            description = description,
            distance = "0.2 km away",
            user = _userProfile.value.name,
            imageUrl = "https://images.unsplash.com/photo-1542838132-92c53300491e?auto=format&fit=crop&q=80&w=400"
        )
        _sharePosts.update { listOf(newPost) + it }
    }
}
