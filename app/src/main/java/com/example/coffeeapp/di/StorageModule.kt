package com.example.coffeeapp.di

import com.example.coffeeapp.data.storage.SharedPreferencesStorageImpl
import com.example.coffeeapp.domain.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {
    @Binds
    abstract fun bindStorage(sharedPreferences: SharedPreferencesStorageImpl):Storage
}