package com.example.nearnestapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nearnestapp.data.MockData
import com.example.nearnestapp.model.CartItem
import com.example.nearnestapp.model.Product
import com.example.nearnestapp.model.SharePost
import com.example.nearnestapp.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NearNestViewModel : ViewModel() {

    private val _userProfile = MutableStateFlow(MockData.userProfile)
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _products = MutableStateFlow(MockData.products)
    val products: StateFlow<List<Product>> = _products.asStateFlow()

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

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }

    fun updateLocation(location: String) {
        _userProfile.update { it.copy(selectedLocation = location) }
    }

    fun updateUserProfile(name: String, email: String, role: String) {
        val initialMode = if (role.equals("SELLER", ignoreCase = true)) "SELLER" else "BUYER"
        _userProfile.update {
            it.copy(name = name, email = email, role = role, currentMode = initialMode)
        }
    }

    fun toggleUserMode() {
        _userProfile.update {
            val newMode = if (it.currentMode == "BUYER") "SELLER" else "BUYER"
            it.copy(currentMode = newMode)
        }
    }

    fun addNewProduct(name: String, price: Int, category: String, unit: String, description: String) {
        val newProd = Product(
            id = "prod_${System.currentTimeMillis()}",
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
    }

    fun addToCart(product: Product) {
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
