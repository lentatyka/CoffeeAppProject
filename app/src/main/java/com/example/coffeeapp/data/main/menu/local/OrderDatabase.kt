package com.example.coffeeapp.data.main.menu.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.di.main.MainScope

@MainScope
@Database(entities = [MenuItem::class], version = 1, exportSchema = false)
abstract class OrderDatabase:RoomDatabase() {
    abstract fun orderDao():OrderDao
}