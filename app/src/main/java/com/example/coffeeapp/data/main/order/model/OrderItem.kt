package com.example.coffeeapp.data.main.order.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class OrderItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val price: Double,
    val amount: Int,
    val ownerId: Long
)
