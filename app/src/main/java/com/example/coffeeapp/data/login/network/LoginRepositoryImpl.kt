package com.example.coffeeapp.data.login.network

import com.example.coffeeapp.di.login.FakeLoginApi
import com.example.coffeeapp.domain.login.network.LoginRepository
import com.example.coffeeapp.domain.login.network.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    @FakeLoginApi private val loginServiceApi: LoginServiceApi
): LoginRepository {
    override suspend fun signUp(user: User): UserInfoDto {
        return loginServiceApi.signUp(user)
    }

    override suspend fun signIn(user: User): UserInfoDto {
        return loginServiceApi.signIn(user)
    }
}