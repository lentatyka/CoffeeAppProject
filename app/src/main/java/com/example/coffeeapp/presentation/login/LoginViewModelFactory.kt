package com.example.coffeeapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coffeeapp.di.login.LoginScope
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
@LoginScope
class LoginViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get() as T
    }
}