package com.example.coffeeapp.domain.login.network

import com.example.coffeeapp.data.login.network.UserInfoDto

interface LoginRepository {
    suspend fun signUp(user: User): UserInfoDto
    suspend fun signIn(user: User): UserInfoDto
}