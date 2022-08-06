package com.example.coffeeapp.data.login.network

import com.example.coffeeapp.domain.login.network.User
import kotlinx.coroutines.delay
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.IOException
import javax.inject.Inject

/**
 * Сервер более не функционирует =( Состряпаем фейковый вариант
 */
interface LoginServiceApi {

    @POST("/auth/register")
    suspend fun signUp(@Body user: User): UserInfoDto

    @POST("/auth/login")
    suspend fun signIn(@Body user: User): UserInfoDto


    class FakeLoginService @Inject constructor() : LoginServiceApi {

        private val token = "token"
        private val users = mutableListOf<User>()

        override suspend fun signUp(user: User): UserInfoDto {
            delay(1000) // Loading from net imitation
            return if (user in users) throw IOException("login already exists")
            else {
                users += user
                UserInfoDto(token, setTokenLifeTime())
            }
        }

        override suspend fun signIn(user: User): UserInfoDto {
            delay(1000) // Loading from net imitation
            return if (user !in users) throw IOException("login not exists")
            else UserInfoDto(token, setTokenLifeTime())
        }

        private fun setTokenLifeTime(): Long {
            //current time + 24 hours
            return System.currentTimeMillis() + 86400000
        }
    }

}