package com.example.coffeeapp.domain.main.shops.model

import com.example.coffeeapp.data.main.shops.remote.Point

data class Shop(
    val id: Long,
    val name: String,
    val point: Point,
    val distance: Int? = null
)