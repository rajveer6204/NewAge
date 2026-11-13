package com.example.nearnestapp.util

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object LocationUtils {

    private const val EARTH_RADIUS_KM = 6371.0

    /**
     * Calculates the distance in kilometers between two geographical coordinates
     * using the Haversine formula.
     */
    fun calculateDistanceKm(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)

        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        val distance = EARTH_RADIUS_KM * c

        // Return rounded to 1 decimal place
        return (distance * 10).toInt() / 10.0
    }

    /**
     * Returns true if a shop at (shopLat, shopLon) is within the user's service radius (default 5.5-6.0 km)
     */
    fun isWithinServiceRadius(
        shopLat: Double,
        shopLon: Double,
        userLat: Double,
        userLon: Double,
        maxRadiusKm: Double = 6.0
    ): Boolean {
        val distance = calculateDistanceKm(shopLat, shopLon, userLat, userLon)
        return distance <= maxRadiusKm
    }

    /**
     * Formats distance in km or meters for UI display
     */
    fun formatDistanceDisplay(distanceKm: Double): String {
        return if (distanceKm < 1.0) {
            "${(distanceKm * 1000).toInt()} m"
        } else {
            "$distanceKm km"
        }
    }
}
