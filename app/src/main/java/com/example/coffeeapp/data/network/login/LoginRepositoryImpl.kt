package com.example.coffeeapp.data.network.login

import com.example.coffeeapp.domain.network.login.LoginRepository
import com.example.coffeeapp.domain.network.login.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginServiceApi: LoginServiceApi
):LoginRepository {
    override suspend fun signUp(user: User): UserInfoDto {
        return loginServiceApi.signUp(user)
    }

    override suspend fun signIn(user: User): UserInfoDto {
        return loginServiceApi.signIn(user)
    }
}