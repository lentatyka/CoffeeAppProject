package com.example.coffeeapp.data.main.order.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "orders", primaryKeys = ["id", "ownerId"])
data class OrderItemDto(
    @ColumnInfo(name = "id")
    val id: Int,
    val name: String,
    val price: Double,
    val amount: Int,
    @ColumnInfo(name = "ownerId")
    val ownerId: Long
)
