package com.example.coffeeapp.domain.network.login

import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.network.login.toUserInfo
import com.example.coffeeapp.domain.storage.Storage
import com.example.coffeeapp.domain.storage.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val storage: Storage
) {
    fun signIn(email: String, password: String): Flow<Resource<UserInfo>> {
        return flow {
            emit(Resource.Loading)
            try {
                val userInfo = loginRepository.signIn(User(email, password)).toUserInfo()
                //Success. Save token to storage
                storage.saveUserInfo(userInfo)
                emit(Resource.Success(userInfo))
            } catch (e: Exception) {
                //Обработать коды ошибок!
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }
    }

    fun signUp(email: String, password: String): Flow<Resource<UserInfo>>{
        return flow {
            emit(Resource.Loading)
            try {
                val userInfo = loginRepository.signUp(User(email, password)).toUserInfo()
                emit(Resource.Success(userInfo))
            }catch (e: Exception){
                //Обработать коды ошибок!
                emit(Resource.Error(e.localizedMessage ?: "temp answer"))
            }
        }
    }
}