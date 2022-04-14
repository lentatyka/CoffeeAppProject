package com.example.coffeeapp.domain.storage

class SaveUserInfoUseCase(private val storage: Storage) {
    operator fun invoke(userInfo: UserInfo) = storage.saveUserInfo(userInfo)
}