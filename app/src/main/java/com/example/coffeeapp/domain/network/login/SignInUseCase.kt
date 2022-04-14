package com.example.coffeeapp.domain.network.login

import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.network.login.toUserInfo
import com.example.coffeeapp.domain.storage.Storage
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val storage: Storage
) {
    operator fun invoke(email: String, password: String) = flow {
        emit(Resource.Loading)
        try {
            val userInfo = loginRepository.signIn(User(email, password)).toUserInfo()
            //Success. Save token to storage
            storage.saveUserInfo(userInfo)
            emit(
                Resource.Success(
                    userInfo
                )
            )
        } catch (e: Exception) {
            //Обработать коды ошибок!
            emit(Resource.Error(e.localizedMessage ?: "temp answer"))
        }
    }
}