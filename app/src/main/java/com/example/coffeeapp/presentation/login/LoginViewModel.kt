package com.example.coffeeapp.presentation.login

import android.text.TextUtils
import android.view.View
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.lifecycle.*
import com.example.coffeeapp.common.Event
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.login.network.UserInfoDto
import com.example.coffeeapp.di.login.ActivityScope
import com.example.coffeeapp.domain.login.network.LoginUseCase
import com.google.android.material.textfield.TextInputEditText
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
        _passwordError.value = true
//        viewModelScope.launch {
//            loginUseCase.signIn(email, password).collect {
//                _status.postValue(Event(it))
//            }
//        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.signUp(email, password).collect {

            }
        }
    }

    fun getPasswordFocusChangeListener() = View.OnFocusChangeListener { _, hasFocus: Boolean ->
        _passwordError.value = !hasFocus
    }

    fun getEmailTextChangeListener() = TextViewBindingAdapter.OnTextChanged { str:CharSequence, _, _, count:Int ->
        _emailError.value = !android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
    }
}