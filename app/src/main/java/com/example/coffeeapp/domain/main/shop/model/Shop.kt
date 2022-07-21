package com.example.coffeeapp.domain.main.shop.model

import com.example.coffeeapp.data.main.shop.remote.Point

data class Shop(
    val id: Long,
    val name: String,
    val point: Point,
    val distance: Int? = null
)