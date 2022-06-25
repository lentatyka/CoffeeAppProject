package com.example.coffeeapp.domain.login.network


import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.login.network.UserInfoDto
import com.example.coffeeapp.domain.login.storage.SaveStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val storage: SaveStorage
) {
    fun signIn(email: String, password: String): Flow<Resource<UserInfoDto>> {
        return flow {
            emit(Resource.Loading)
            try {
                val userInfo = loginRepository.signIn(User(email, password))
                //Success. Save token to storage
                storage(userInfo)
                emit(Resource.Success(userInfo))
            } catch (e: HttpException) {
                //Обработать коды ошибок!
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }
    }

    fun signUp(email: String, password: String): Flow<Resource<UserInfoDto>> {
        return flow {
            emit(Resource.Loading)
            try {
                val userInfo = loginRepository.signUp(User(email, password))
                emit(Resource.Success(userInfo))
            } catch (e: HttpException) {
                //Обработать коды ошибок!
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }
    }
}