package com.example.coffeeapp.di.main

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.coffeeapp.data.main.menu.local.OrderDao
import com.example.coffeeapp.data.main.menu.local.OrderDatabase
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    fun provideRoomDao(applicationContext: Application): OrderDao {
        return Room.databaseBuilder(
            applicationContext,
            OrderDatabase::class.java, "order-db"
        )
            .build()
            .orderDao()
    }
}