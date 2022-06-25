package com.example.coffeeapp.domain.storage

class LoadUserInfoUseCase(private val storage: LoadStorage) {
    operator fun invoke() = storage()
}