package com.example.coffeeapp.data.main.menu.local

import android.view.MenuItem
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MenuItem::class], version = 1, exportSchema = false)
abstract class OrderDatabase:RoomDatabase() {
    abstract fun orderDao():OrderDao
}