package com.example.coffeeapp.domain.login.storage

import com.example.coffeeapp.data.login.network.UserInfoDto
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(private val storage: SaveStorage) {
    operator fun invoke(userInfo: UserInfoDto) = storage(userInfo)
}