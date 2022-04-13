package com.example.coffeeapp.domain.network.login

import com.example.coffeeapp.data.network.login.UserInfoDto

interface LoginRepository {
    suspend fun signUp(user: User): UserInfoDto
    suspend fun signIn(user: User):UserInfoDto
}