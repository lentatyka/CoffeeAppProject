package com.example.coffeeapp.domain.storage

interface Storage {
    fun loadUserInfo():UserInfo
    fun saveUserInfo(userInfo: UserInfo)
}