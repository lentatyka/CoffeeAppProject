package com.example.coffeeapp.di.storage

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.coffeeapp.common.Constants.USER_PREFERENCES
import dagger.Module
import dagger.Provides

@Module
class StorageModule {
    @Provides
    fun provideSharedPreferences(app: Application):SharedPreferences{
        return app.getSharedPreferences(
            USER_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }
}