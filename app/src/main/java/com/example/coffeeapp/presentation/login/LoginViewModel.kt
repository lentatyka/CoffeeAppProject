package com.example.coffeeapp.presentation.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coffeeapp.domain.network.login.SignInUseCase
import com.example.coffeeapp.domain.network.login.SignUpUseCase
import com.example.coffeeapp.domain.storage.SaveUserInfoUseCase

class LoginViewModel(
    app: Application,
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
):AndroidViewModel(app) {

    fun signIn(email: String, password: String) = signInUseCase(email, password)

    fun signUp(email: String, password: String) = signUpUseCase(email, password)
}