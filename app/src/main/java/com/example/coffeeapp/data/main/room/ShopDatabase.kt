package com.example.coffeeapp.data.main.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coffeeapp.data.main.menu.local.MenuDao
import com.example.coffeeapp.data.main.order.local.OrderDao
import com.example.coffeeapp.data.main.order.model.OrderItemDto

@Database(entities = [OrderItemDto::class], version = 1, exportSchema = false)
abstract class ShopDatabase:RoomDatabase() {
    abstract fun orderDao(): OrderDao
    abstract fun menuDao(): MenuDao
}