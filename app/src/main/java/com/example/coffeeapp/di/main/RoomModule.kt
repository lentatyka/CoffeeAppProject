package com.example.coffeeapp.di.main

import android.app.Application
import androidx.room.Room
import com.example.coffeeapp.data.main.room.ShopDatabase
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    fun provideRoomDatabase(applicationContext: Application): ShopDatabase {
        return Room.databaseBuilder(
            applicationContext,
            ShopDatabase::class.java, "order-db"
        ).build()
    }

    @Provides
    fun provideOrderDao(roomDatabase: ShopDatabase) = roomDatabase.orderDao()

    @Provides
    fun provideMenuDao(roomDatabase: ShopDatabase) = roomDatabase.menuDao()

}