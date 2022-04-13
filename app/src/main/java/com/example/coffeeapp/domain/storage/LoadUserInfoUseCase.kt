package com.example.coffeeapp.domain.storage

class LoadUserInfoUseCase(private val storage: Storage) {
    operator fun invoke() = storage.loadUserInfo()
}