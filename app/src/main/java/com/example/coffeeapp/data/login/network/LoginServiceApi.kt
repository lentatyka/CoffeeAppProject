package com.example.coffeeapp.data.login.network

import com.example.coffeeapp.domain.login.network.User
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject

/**
 * Сервер более не функционирует =( Состряпаем фейковый вариант
 */
interface LoginServiceApi {

    @POST("/auth/register")
    suspend fun signUp(@Body user: User): UserInfoDto

    @POST("/auth/login")
    suspend fun signIn(@Body user: User): UserInfoDto


    class FakeLoginService @Inject constructor(): LoginServiceApi {

        private val token = "token"

        override suspend fun signUp(user: User): UserInfoDto {
            return UserInfoDto(token, setTokenLifeTime())
        }

        override suspend fun signIn(user: User): UserInfoDto {
            return UserInfoDto(token, setTokenLifeTime())
        }

        private fun setTokenLifeTime(): Long {
            //current time + 24 hours
            return System.currentTimeMillis()+86400000
        }
    }

}