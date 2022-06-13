package com.example.coffeeapp.presentation.login

import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.lifecycle.*
import com.example.coffeeapp.common.Event
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.login.network.UserInfoDto
import com.example.coffeeapp.di.login.ActivityScope
import com.example.coffeeapp.domain.login.network.LoginUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _emailError = MutableLiveData<Boolean>()
    val emailError: LiveData<Boolean> get() = _emailError

    private val _passwordError = MutableLiveData<Boolean>()
    val passwordError: LiveData<Boolean> get() = _passwordError

    private val _cPasswordError = MutableLiveData<Boolean>()
    val cPasswordError: LiveData<Boolean> get() = _cPasswordError

    private val _status = MutableLiveData<Event<Resource<UserInfoDto>>>()
    val status: LiveData<Event<Resource<UserInfoDto>>> get() = _status

    fun signIn(email: String, password: String) {
        if(!isEmailValid(email)){
            _emailError.value = true
            return
        }
        if(!isPasswordValid(password)){
            _passwordError.value = true
            return
        }
        viewModelScope.launch {
            loginUseCase.signIn(email, password).collect {
                _status.postValue(Event(it))
            }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.signUp(email, password).collect {

            }
        }
    }

    fun getPasswordTextChangeListener() =
        TextViewBindingAdapter.OnTextChanged { str: CharSequence, _, _, count: Int ->
            _passwordError.value = !isPasswordValid(str)
        }

    fun getEmailTextChangeListener() =
        TextViewBindingAdapter.OnTextChanged { str: CharSequence, _, _, count: Int ->
            _emailError.value = !isEmailValid(str)
        }

    private fun isEmailValid(email: CharSequence) =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordValid(password: CharSequence) = password.isNotEmpty()
}