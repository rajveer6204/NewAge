package com.example.nearnestapp.navigation

object NavRoutes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val LOCATION = "location"
    const val HOME = "home"
    const val SEARCH = "search"
    const val PRODUCT_DETAILS = "product_details/{productId}"
    const val SHARE = "share"
    const val CART = "cart"
    const val ORDER_TRACKING = "order_tracking"
    const val PROFILE = "profile"

    fun productDetailsRoute(productId: String) = "product_details/$productId"
}
