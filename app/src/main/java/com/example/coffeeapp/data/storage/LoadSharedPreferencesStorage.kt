package com.example.coffeeapp.data.storage

import android.content.SharedPreferences
import com.example.coffeeapp.common.Constants.TOKEN
import com.example.coffeeapp.common.Constants.TOKEN_LIFETIME
import com.example.coffeeapp.data.login.network.UserInfoDto
import com.example.coffeeapp.domain.storage.LoadStorage
import javax.inject.Inject

class LoadSharedPreferencesStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences
): LoadStorage {
    override fun invoke(): UserInfoDto {
        return UserInfoDto(
            sharedPreferences.getString(TOKEN, null),
            sharedPreferences.getLong(TOKEN_LIFETIME, 0)
        )
    }
}