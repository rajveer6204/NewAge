package com.example.nearnestapp.data

import com.example.nearnestapp.model.CampusGroup
import com.example.nearnestapp.model.CategoryItem
import com.example.nearnestapp.model.OrderRecord
import com.example.nearnestapp.model.OrderStep
import com.example.nearnestapp.model.Product
import com.example.nearnestapp.model.SellerStats
import com.example.nearnestapp.model.SharePost
import com.example.nearnestapp.model.Shop
import com.example.nearnestapp.model.UrgentAlert
import com.example.nearnestapp.model.UserProfile

object MockData {
    val userProfile = UserProfile()
    val sellerStats = SellerStats()

    val categories = listOf(
        CategoryItem("1", "Grocery", 0xFFFFECE0),
        CategoryItem("2", "Food & Dining", 0xFFFFEBEB),
        CategoryItem("3", "Stationery", 0xFFE0F2FE),
        CategoryItem("4", "Bakery", 0xFFF3E8FF),
        CategoryItem("5", "Fruits & Veggies", 0xFFDCFCE7),
        CategoryItem("6", "Daily Essentials", 0xFFFFFBEB)
    )

    val shops = listOf(
        Shop(
            id = "shop_1",
            name = "Sharma Kirana & Supermarket",
            category = "Grocery",
            imageUrl = "https://images.unsplash.com/photo-1578916171728-46686eac8d58?auto=format&fit=crop&q=80&w=400",
            distanceKm = 0.8,
            rating = 4.8,
            reviewsCount = "450 ratings",
            isOpen = true,
            openTime = "07:30 AM",
            closeTime = "09:30 PM",
            address = "Shop #4, Green Valley Society Market",
            society = "Green Valley Society",
            deliveryRadiusKm = 5.5,
            ownerName = "Sharma Ji",
            ownerPhone = "+91 9812345678"
        ),
        Shop(
            id = "shop_2",
            name = "Delish Bites & Bakers",
            category = "Food & Dining",
            imageUrl = "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?auto=format&fit=crop&q=80&w=400",
            distanceKm = 1.2,
            rating = 4.6,
            reviewsCount = "820 ratings",
            isOpen = true,
            openTime = "10:00 AM",
            closeTime = "11:00 PM",
            address = "Food Court, Block B, Campus Area",
            society = "Campus Commercial Complex",
            deliveryRadiusKm = 6.0,
            ownerName = "Chef Aarav",
            ownerPhone = "+91 9823456789"
        ),
        Shop(
            id = "shop_3",
            name = "Gupta Book Depot & Stationery",
            category = "Stationery",
            imageUrl = "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?auto=format&fit=crop&q=80&w=400",
            distanceKm = 1.5,
            rating = 4.9,
            reviewsCount = "610 ratings",
            isOpen = true,
            openTime = "08:30 AM",
            closeTime = "08:30 PM",
            address = "Shop #12, Academic Block C",
            society = "Academic Zone",
            deliveryRadiusKm = 5.0,
            ownerName = "Gupta Ji",
            ownerPhone = "+91 9834567890"
        ),
        Shop(
            id = "shop_4",
            name = "Fresh Organic Fruits & Veggies",
            category = "Fruits & Veggies",
            imageUrl = "https://images.unsplash.com/photo-1610832958506-aa56368176cf?auto=format&fit=crop&q=80&w=400",
            distanceKm = 0.5,
            rating = 4.7,
            reviewsCount = "340 ratings",
            isOpen = true,
            openTime = "06:30 AM",
            closeTime = "09:00 PM",
            address = "Stall 2, Society Entrance Gate",
            society = "Green Valley Society",
            deliveryRadiusKm = 5.0,
            ownerName = "Ramesh Kumar",
            ownerPhone = "+91 9845678901"
        )
    )

    val campusGroups = listOf(
        CampusGroup(
            id = "group_marketplace",
            title = "Everyday Marketplace",
            description = "Order spiral notebooks, pilot pens, scientific calculators, snacks, and phone chargers directly from sellers in your building.",
            actionText = "Browse Catalog",
            colorHex = 0xFFF0FDF4,
            iconType = "store"
        ),
        CampusGroup(
            id = "group_borrow",
            title = "Borrow & Lend",
            description = "Why buy an expensive item for a 2-hour exam or single presentation? Borrow drafters, chargers, calculators, or lab coats easily.",
            actionText = "View Borrowables",
            colorHex = 0xFFEFF6FF,
            iconType = "handshake"
        ),
        CampusGroup(
            id = "group_runners",
            title = "Student Runners",
            description = "Hostel students heading down to the cafeteria or shop can pick up and deliver your order, earning instant delivery fees.",
            actionText = "Become a Runner",
            colorHex = 0xFFFFFBEB,
            iconType = "runner"
        ),
        CampusGroup(
            id = "group_urgent",
            title = "Urgent Broadcast",
            description = "Have a critical need right before a viva or presentation? Post an urgent alert and let anyone in the building step up to help.",
            actionText = "See Urgent Needs",
            colorHex = 0xFFFEF2F2,
            iconType = "alert"
        )
    )

    val urgentAlerts = listOf(
        UrgentAlert(
            id = "alert_1",
            title = "Urgent: Need Engineering Drafter for 2 PM Viva",
            description = "Left my drafter in hometown, need for 1 hour exam in Lab 3.",
            roomOrBlock = "Block B, Room 304",
            postedBy = "Rohit M.",
            timestamp = "10 mins ago"
        ),
        UrgentAlert(
            id = "alert_2",
            title = "Type-C Fast Charger needed for Presentation",
            description = "Phone at 2% before project demo, urgent 15 min charge needed.",
            roomOrBlock = "Hostel 4, Room 108",
            postedBy = "Ananya T.",
            timestamp = "25 mins ago"
        )
    )

    val products = listOf(
        Product(
            id = "amul_milk",
            shopId = "shop_1",
            name = "Amul Milk",
            price = 60,
            originalPrice = 70,
            discountPercent = "14% OFF",
            rating = 4.5,
            reviewsCount = "12k reviews",
            distance = "0.8 km",
            imageUrl = "https://images.unsplash.com/photo-1550583724-b2692b85b150?auto=format&fit=crop&q=80&w=400",
            unit = "500 ml",
            description = "Fresh & healthy milk for your family.",
            category = "Grocery"
        ),
        Product(
            id = "notebook",
            shopId = "shop_3",
            name = "Spiral Notebook (200 pgs)",
            price = 60,
            originalPrice = 80,
            discountPercent = "25% OFF",
            rating = 4.8,
            reviewsCount = "450 reviews",
            distance = "1.5 km",
            imageUrl = "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?auto=format&fit=crop&q=80&w=400",
            unit = "1 Piece",
            description = "Unruled 200-page spiral notebook for lectures and notes.",
            category = "Stationery"
        ),
        Product(
            id = "pilot_pen",
            shopId = "shop_3",
            name = "Pilot V5 Pen (Blue)",
            price = 40,
            originalPrice = 50,
            discountPercent = "20% OFF",
            rating = 4.9,
            reviewsCount = "620 reviews",
            distance = "1.5 km",
            imageUrl = "https://images.unsplash.com/photo-1585336261026-8f5786372960?auto=format&fit=crop&q=80&w=400",
            unit = "1 Pen",
            description = "Smooth 0.5mm precision liquid ink pen.",
            category = "Stationery"
        ),
        Product(
            id = "calculator",
            shopId = "shop_3",
            name = "Casio fx-991EX Calculator",
            price = 650,
            originalPrice = 850,
            discountPercent = "23% OFF",
            rating = 4.9,
            reviewsCount = "890 reviews",
            distance = "1.5 km",
            imageUrl = "https://images.unsplash.com/photo-1594980596870-8aa52a78d8cd?auto=format&fit=crop&q=80&w=400",
            unit = "1 Unit",
            description = "Scientific calculator ideal for engineering & math exams.",
            category = "Stationery"
        ),
        Product(
            id = "drafter_borrow",
            shopId = "shop_3",
            name = "Engineering Mini Drafter",
            price = 0,
            originalPrice = null,
            discountPercent = "FREE BORROW",
            rating = 4.7,
            reviewsCount = "120 borrows",
            distance = "1.5 km",
            imageUrl = "https://images.unsplash.com/photo-1581092160607-ee22621dd758?auto=format&fit=crop&q=80&w=400",
            unit = "Free 2-Hour Borrow",
            description = "Borrow for your engineering drawing lab exam today.",
            category = "Borrowables"
        ),
        Product(
            id = "charger_borrow",
            shopId = "shop_1",
            name = "65W Type-C Fast Charger",
            price = 0,
            originalPrice = null,
            discountPercent = "FREE BORROW",
            rating = 4.8,
            reviewsCount = "310 borrows",
            distance = "0.8 km",
            imageUrl = "https://images.unsplash.com/photo-1583863788434-e58a36330cf0?auto=format&fit=crop&q=80&w=400",
            unit = "Free 1-Hour Borrow",
            description = "Quick laptop & phone charger for emergency study sessions.",
            category = "Borrowables"
        ),
        Product(
            id = "mother_dairy_milk",
            shopId = "shop_1",
            name = "Mother Dairy Milk",
            price = 58,
            rating = 4.4,
            reviewsCount = "8k reviews",
            distance = "0.8 km",
            imageUrl = "https://images.unsplash.com/photo-1563636619-e9143da7973b?auto=format&fit=crop&q=80&w=400",
            unit = "500 ml",
            description = "Pasteurized full cream milk rich in calcium.",
            category = "Grocery"
        ),
        Product(
            id = "apple",
            shopId = "shop_4",
            name = "Fresh Organic Apple",
            price = 50,
            originalPrice = 65,
            discountPercent = "23% OFF",
            rating = 4.6,
            reviewsCount = "9k reviews",
            distance = "0.5 km",
            imageUrl = "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?auto=format&fit=crop&q=80&w=400",
            unit = "500g",
            description = "Crispy juicy red apples from Shimla.",
            category = "Fruits & Veggies"
        ),
        Product(
            id = "bread",
            shopId = "shop_2",
            name = "Fresh Loaf Bread",
            price = 40,
            rating = 4.5,
            reviewsCount = "15k reviews",
            distance = "1.2 km",
            imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?auto=format&fit=crop&q=80&w=400",
            unit = "400g",
            description = "Freshly baked brown whole wheat bread.",
            category = "Bakery"
        )
    )

    val sellerOrders = listOf(
        OrderRecord(
            orderId = "ORD-9481",
            customerId = "cust_101",
            shopId = "shop_1",
            shopName = "Sharma Kirana & Supermarket",
            customerName = "Anish Sharma",
            customerPhone = "+91 9876543211",
            deliveryAddress = "Flat 402, Block A, Green Valley Society",
            items = listOf(),
            subtotal = 160,
            deliveryFee = 15,
            total = 175,
            status = "Order Placed",
            timestamp = "10 mins ago",
            deliveryInstructions = "Ring bell and leave at door"
        ),
        OrderRecord(
            orderId = "ORD-9479",
            customerId = "cust_102",
            shopId = "shop_1",
            shopName = "Sharma Kirana & Supermarket",
            customerName = "Priya Verma",
            customerPhone = "+91 9876543222",
            deliveryAddress = "Flat 105, Block C, Green Valley Society",
            items = listOf(),
            subtotal = 320,
            deliveryFee = 15,
            total = 335,
            status = "Seller Accepted",
            timestamp = "25 mins ago",
            deliveryInstructions = "Call when near entrance gate"
        ),
        OrderRecord(
            orderId = "ORD-9475",
            customerId = "cust_103",
            shopId = "shop_1",
            shopName = "Sharma Kirana & Supermarket",
            customerName = "Vikas Patel",
            customerPhone = "+91 9876543333",
            deliveryAddress = "Flat 601, Block B, Green Valley Society",
            items = listOf(),
            subtotal = 650,
            deliveryFee = 20,
            total = 670,
            status = "Delivered",
            timestamp = "2 hours ago",
            deliveryInstructions = "Handover to security if unavailable"
        )
    )

    val sharePosts = listOf(
        SharePost(
            id = "share_1",
            title = "Fresh Apples (2kg)",
            description = "Excess apples from my orchard, willing to share with neighbors.",
            distance = "0.4 km away",
            user = "Priya S.",
            imageUrl = "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?auto=format&fit=crop&q=80&w=400"
        ),
        SharePost(
            id = "share_2",
            title = "Unopened Milk Packets",
            description = "Going out of town for weekend, free fresh milk packets.",
            distance = "0.8 km away",
            user = "Rahul M.",
            imageUrl = "https://images.unsplash.com/photo-1550583724-b2692b85b150?auto=format&fit=crop&q=80&w=400"
        )
    )

    val orderSteps = listOf(
        OrderStep(1, "Order Placed", "10:30 AM", isCompleted = true, isActive = false),
        OrderStep(2, "Seller Accepted", "10:32 AM", isCompleted = true, isActive = false),
        OrderStep(3, "Preparing Order", "10:35 AM", isCompleted = true, isActive = false),
        OrderStep(4, "Out for Delivery", "11:00 AM", isCompleted = false, isActive = true),
        OrderStep(5, "Delivered", "Soon...", isCompleted = false, isActive = false)
    )
}
