package com.example.coffeeapp.domain.network.login

import com.example.coffeeapp.data.network.login.toUserInfo
import com.example.coffeeapp.domain.storage.UserInfo

class SignUpUseCase(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(user: User):UserInfo{
        return loginRepository.signUp(user).toUserInfo()
    }
}