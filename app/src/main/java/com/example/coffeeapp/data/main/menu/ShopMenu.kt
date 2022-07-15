package com.example.coffeeapp.data.main.menu

data class ShopMenu(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Double,
    var amount: Int = 0
)
