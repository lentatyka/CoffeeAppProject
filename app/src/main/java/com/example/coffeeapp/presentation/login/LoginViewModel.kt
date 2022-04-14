package com.example.coffeeapp.presentation.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.domain.network.login.SignInUseCase
import com.example.coffeeapp.domain.network.login.SignUpUseCase
import com.example.coffeeapp.domain.storage.UserInfo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    app: Application,
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
) : AndroidViewModel(app) {

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> get() = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> get() = _passwordError

    private val _cPasswordError = MutableLiveData<String?>()
    val cPasswordError: LiveData<String?> get() = _cPasswordError

    private val _status = MutableLiveData<Resource<UserInfo>>()
    val status: LiveData<Resource<UserInfo>> get() = _status

    fun signIn(email: String, password: String) {
        signInUseCase(email, password).onEach { info ->
            _status.postValue(info)
        }.launchIn(viewModelScope)
    }

    fun test(st: String){
        Log.d("TAG", st)
    }

    fun signUp(email: String, password: String) = signUpUseCase(email, password)
}