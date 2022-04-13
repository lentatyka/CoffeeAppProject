package com.example.coffeeapp.domain.storage

interface Storage {
    fun loadUserInfo():User
    fun saveUserInfo(user: User)
}