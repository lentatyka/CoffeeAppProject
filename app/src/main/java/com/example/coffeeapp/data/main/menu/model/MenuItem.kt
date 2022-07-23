package com.example.coffeeapp.data.main.menu.model

import androidx.room.Entity

@Entity(tableName = "order")
data class MenuItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Double,
    var amount: Int = 0
)
