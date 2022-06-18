package com.example.coffeeapp.data.main.storage

import android.content.SharedPreferences
import com.example.coffeeapp.common.Constants.TOKEN
import com.example.coffeeapp.common.Constants.TOKENLIFETIME
import com.example.coffeeapp.data.login.network.UserInfoDto
import com.example.coffeeapp.domain.main.storage.LoadStorage
import javax.inject.Inject

class LoadSharedPreferencesStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences
):LoadStorage {
    override fun invoke(): UserInfoDto {
        return UserInfoDto(
            sharedPreferences.getString(TOKEN, null),
            sharedPreferences.getLong(TOKENLIFETIME, 0)
        )
    }
}