package com.example.coffeeapp.data.storage

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.coffeeapp.domain.storage.Storage
import com.example.coffeeapp.domain.storage.UserInfo
import javax.inject.Inject

private const val USER_PREFERENCES = "userPreferences"
private const val TOKEN = "token"
private const val TOKENLIFETIME = "tokenLifetime"

class SharedPreferencesStorageImpl @Inject constructor(app: Application) : Storage {
    private val preferences: SharedPreferences = app.applicationContext
        .applicationContext.getSharedPreferences(
            USER_PREFERENCES,
            Context.MODE_PRIVATE
        )

    override fun loadUserInfo(): UserInfo {
        return UserInfo(
            preferences.getString(TOKEN, null),
            preferences.getLong(TOKENLIFETIME, 0)
        )
    }

    override fun saveUserInfo(userInfo: UserInfo) {
        // lifetime = 1 hour
        val expirationTime = System.currentTimeMillis() + userInfo.tokenLifeTime
        preferences.edit()
            .putString(TOKEN, userInfo.token)
            .putLong(TOKENLIFETIME, expirationTime)
            .apply()
    }
}