package com.example.nearnestapp.navigation

object NavRoutes {
    const val SPLASH = "splash"
    const val ROLE_SELECTION = "role_selection"
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val BUYER_REGISTRATION = "buyer_registration"
    const val BUYER_LOGIN = "buyer_login"
    const val SELLER_REGISTRATION = "seller_registration"
    const val SELLER_LOGIN = "seller_login"
    const val SHOP_REGISTRATION = "shop_registration"
    const val LOCATION = "location"
    const val HOME = "home"
    const val SEARCH = "search"
    const val PRODUCT_DETAILS = "product_details/{productId}"
    const val SHOP_DETAILS = "shop_details/{shopId}"
    const val SHARE = "share"
    const val CART = "cart"
    const val ORDER_TRACKING = "order_tracking"
    const val PROFILE = "profile"

    fun productDetailsRoute(productId: String) = "product_details/$productId"
    fun shopDetailsRoute(shopId: String) = "shop_details/$shopId"
}
