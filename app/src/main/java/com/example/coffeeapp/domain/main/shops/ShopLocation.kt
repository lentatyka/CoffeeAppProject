package com.example.coffeeapp.domain.main.shops

import com.example.coffeeapp.data.main.shops.remote.Point

data class ShopLocation(
    val id: Int,
    val name: String,
    val point: Point,
    val distance: Double? = null
)