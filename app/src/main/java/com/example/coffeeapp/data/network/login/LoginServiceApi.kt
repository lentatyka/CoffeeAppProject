package com.example.coffeeapp.data.network.login

import com.example.coffeeapp.domain.network.login.User
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginServiceApi {

    @POST("/auth/register")
    suspend fun signUp(@Body user: User): UserInfoDto

    @POST("/auth/login")
    suspend fun signIn(@Body user: User): UserInfoDto

}