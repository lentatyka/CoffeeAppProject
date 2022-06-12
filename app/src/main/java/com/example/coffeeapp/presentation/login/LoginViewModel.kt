package com.example.coffeeapp.presentation.login

import androidx.lifecycle.*
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.di.ActivityScope
import com.example.coffeeapp.domain.network.login.LoginUseCase
import com.example.coffeeapp.domain.storage.UserInfo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> get() = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> get() = _passwordError

    private val _cPasswordError = MutableLiveData<String?>()
    val cPasswordError: LiveData<String?> get() = _cPasswordError

    private val _status = MutableLiveData<Resource<UserInfo>>()
    val status: LiveData<Resource<UserInfo>> get() = _status

    fun signIn(email: String, password: String) {
        loginUseCase.signIn(email, password).onEach { info ->
            _status.postValue(info)
        }.launchIn(viewModelScope)
    }

    fun signUp(email: String, password: String) = loginUseCase.signUp(email, password)
}