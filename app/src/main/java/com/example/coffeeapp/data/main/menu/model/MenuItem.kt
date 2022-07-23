package com.example.coffeeapp.data.main.menu.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class MenuItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Double,
    var amount: Int = 0
)
