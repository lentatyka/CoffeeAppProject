package com.example.coffeeapp.data.login.storage

import android.content.SharedPreferences
import com.example.coffeeapp.common.Constants
import com.example.coffeeapp.data.login.network.UserInfoDto
import com.example.coffeeapp.domain.login.storage.SaveStorage
import javax.inject.Inject

class SaveSharedPreferencesStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences
):SaveStorage {
    override fun invoke(userInfo: UserInfoDto) {
        sharedPreferences.edit()
            .putString(Constants.TOKEN, userInfo.token)
            .putLong(Constants.TOKENLIFETIME, userInfo.tokenLifeTime)
            .apply()
    }
}