package com.example.coffeeapp.domain.main.storage

class LoadUserInfoUseCase(private val storage: LoadStorage) {
    operator fun invoke() = storage()
}