package com.example.coffeeapp.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.coffeeapp.domain.storage.Storage
import com.example.coffeeapp.domain.storage.User

private const val USER_PREFERENCES = "userPreferences"
private const val TOKEN = "token"
private const val TOKENLIFETIME = "tokenLifetime"
class StorageImpl(context: Context):Storage {
    private val preferences: SharedPreferences = context.applicationContext.getSharedPreferences(
        USER_PREFERENCES,
        Context.MODE_PRIVATE
    )
    override fun loadUserInfo(): User {
        return User(
            preferences.getString(TOKEN, null),
            preferences.getLong(TOKENLIFETIME, 0)
        )
    }

    override fun saveUserInfo(user: User) {
        // lifetime = 1 hour
        val expirationTime = System.currentTimeMillis() + user.tokenLifeTime
        preferences.edit()
            .putString(TOKEN, user.token)
            .putLong(TOKENLIFETIME, expirationTime)
            .apply()
    }
}