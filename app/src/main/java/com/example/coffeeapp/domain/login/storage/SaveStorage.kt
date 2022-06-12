package com.example.coffeeapp.domain.login.storage

import com.example.coffeeapp.data.login.network.UserInfoDto

interface SaveStorage {
    operator fun invoke(userInfo: UserInfoDto)
}