package com.example.coffeeapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.common.Event
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.login.network.UserInfoDto
import com.example.coffeeapp.di.login.LoginScope
import com.example.coffeeapp.domain.login.network.LoginUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@LoginScope
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _emailError = MutableLiveData<Event<Boolean>>()
    val emailError: LiveData<Event<Boolean>> get() = _emailError

    private val _passwordError = MutableLiveData<Event<Boolean>>()
    val passwordError: LiveData<Event<Boolean>> get() = _passwordError

    private val _cPasswordError = MutableLiveData<Event<Boolean>>()
    val cPasswordError: LiveData<Event<Boolean>> get() = _cPasswordError

    private val _status = MutableLiveData<Event<Resource<UserInfoDto>>>()
    val status: LiveData<Event<Resource<UserInfoDto>>> get() = _status

    fun signIn(email: String, password: String) {
        if (!isEmailValid(email)) {
            _emailError.value = Event(true)
            return
        }
        if (!isPasswordValid(password)) {
            _passwordError.value = Event(true)
            return
        }
        viewModelScope.launch {
            loginUseCase.signIn(email, password).collect {
                _status.postValue(Event(it))
            }
        }
    }

    fun signUp(email: String, password: String, confirmPassword: String) {
        if (!isEmailValid(email)) {
            _emailError.value = Event(true)
            return
        }
        if (!isPasswordValid(password)) {
            _passwordError.value = Event(true)
            return
        }
        if (password != confirmPassword) {
            _cPasswordError.value = Event(true)
            return
        }
        viewModelScope.launch {
            loginUseCase.signUp(email, password).collect {
                _status.postValue(Event(it))
            }
        }
    }

    private fun isEmailValid(email: CharSequence) =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordValid(password: CharSequence) = password.isNotEmpty()
}