package com.example.coffeeapp.domain.network.login

import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.network.login.toUserInfo
import com.example.coffeeapp.domain.storage.UserInfo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    operator fun invoke(email: String, password: String) = flow {
        emit(Resource.Loading)
        try {
            emit(
                Resource.Success(
                loginRepository.signUp(User(email, password)).toUserInfo()
            ))
        }catch (e: Exception){
            //Обработать коды ошибок!
            emit(Resource.Error(e.localizedMessage ?: "temp answer"))
        }
    }
}