package com.example.nearnestapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nearnestapp.data.MockData
import com.example.nearnestapp.ui.screens.CartScreen
import com.example.nearnestapp.ui.screens.HomeScreen
import com.example.nearnestapp.ui.screens.LocationScreen
import com.example.nearnestapp.ui.screens.LoginScreen
import com.example.nearnestapp.ui.screens.OrderTrackingScreen
import com.example.nearnestapp.ui.screens.ProductDetailsScreen
import com.example.nearnestapp.ui.screens.ProfileScreen
import com.example.nearnestapp.ui.screens.SearchScreen
import com.example.nearnestapp.ui.screens.ShareEssentialsScreen
import com.example.nearnestapp.ui.screens.SignupScreen
import com.example.nearnestapp.ui.screens.SplashScreen
import com.example.nearnestapp.viewmodel.NearNestViewModel

@Composable
fun NearNestNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: NearNestViewModel = viewModel()
) {
    val userProfile by viewModel.userProfile.collectAsState()
    val products by viewModel.products.collectAsState()
    val cartItems by viewModel.cartItems.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val wishlist by viewModel.wishlist.collectAsState()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.SPLASH
    ) {
        // Screen 1: Splash
        composable(NavRoutes.SPLASH) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(NavRoutes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        // Screen 2: Login
        composable(NavRoutes.LOGIN) {
            LoginScreen(
                onLoginSuccess = { name, email, role ->
                    viewModel.updateUserProfile(name, email, role)
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToSignup = {
                    navController.navigate(NavRoutes.SIGNUP)
                }
            )
        }

        // Sign Up Screen
        composable(NavRoutes.SIGNUP) {
            SignupScreen(
                onSignupSuccess = { name, email, role ->
                    viewModel.updateUserProfile(name, email, role)
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.SIGNUP) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        // Screen 3: Location Selection
        composable(NavRoutes.LOCATION) {
            LocationScreen(
                currentLocation = userProfile.selectedLocation,
                onLocationSelected = { loc -> viewModel.updateLocation(loc) },
                onLocationConfirmed = {
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.LOCATION) { inclusive = true }
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // Screen 4: Home Dashboard
        composable(NavRoutes.HOME) {
            HomeScreen(
                userName = userProfile.name.split(" ").firstOrNull() ?: "Badal",
                onProductClick = { productId ->
                    navController.navigate(NavRoutes.productDetailsRoute(productId))
                },
                onNavigateToSearch = { navController.navigate(NavRoutes.SEARCH) },
                onAddToCart = { product -> viewModel.addToCart(product) },
                onNavigateTab = { route ->
                    if (route != NavRoutes.HOME) {
                        navController.navigate(route) {
                            popUpTo(NavRoutes.HOME) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }

        // Screen 5: Search Products
        composable(NavRoutes.SEARCH) {
            SearchScreen(
                searchQuery = searchQuery,
                onQueryChange = { query -> viewModel.updateSearchQuery(query) },
                onProductClick = { productId ->
                    navController.navigate(NavRoutes.productDetailsRoute(productId))
                },
                onAddToCart = { product -> viewModel.addToCart(product) },
                onBack = { navController.popBackStack() }
            )
        }

        // Screen 6: Product Details
        composable(
            route = NavRoutes.PRODUCT_DETAILS,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: "amul_milk"
            val product = products.find { it.id == productId } ?: MockData.products.first()
            val isWishlisted = wishlist.contains(product.id)

            ProductDetailsScreen(
                product = product,
                isWishlisted = isWishlisted,
                onToggleWishlist = { viewModel.toggleWishlist(product.id) },
                onAddToCart = { qty ->
                    repeat(qty) { viewModel.addToCart(product) }
                    navController.navigate(NavRoutes.CART)
                },
                onBack = { navController.popBackStack() }
            )
        }

        // Screen 7: Share Essentials
        composable(NavRoutes.SHARE) {
            ShareEssentialsScreen(
                onCreateSharePostClick = {
                    viewModel.addSharePost("Shared Groceries", "Extra grocery items shared nearby.")
                    navController.navigate(NavRoutes.HOME)
                },
                onSkipClick = { navController.navigate(NavRoutes.HOME) },
                onBack = { navController.popBackStack() },
                onNavigateTab = { route ->
                    navController.navigate(route) {
                        popUpTo(NavRoutes.HOME) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        // Screen 8: Cart & Checkout
        composable(NavRoutes.CART) {
            CartScreen(
                cartItems = cartItems,
                onUpdateQuantity = { productId, delta ->
                    viewModel.updateQuantity(productId, delta)
                },
                onCheckout = {
                    navController.navigate(NavRoutes.ORDER_TRACKING)
                },
                onBack = { navController.popBackStack() },
                onNavigateTab = { route ->
                    navController.navigate(route) {
                        popUpTo(NavRoutes.HOME) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        // Screen 9: Order Tracking
        composable(NavRoutes.ORDER_TRACKING) {
            OrderTrackingScreen(
                onBack = { navController.navigate(NavRoutes.HOME) }
            )
        }

        // Screen 10: Profile & Orders
        composable(NavRoutes.PROFILE) {
            ProfileScreen(
                userProfile = userProfile,
                onNavigateToOrders = { navController.navigate(NavRoutes.CART) },
                onNavigateToShares = { navController.navigate(NavRoutes.SHARE) },
                onNavigateToLocation = { navController.navigate(NavRoutes.LOCATION) },
                onLogout = {
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onNavigateTab = { route ->
                    navController.navigate(route) {
                        popUpTo(NavRoutes.HOME) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
