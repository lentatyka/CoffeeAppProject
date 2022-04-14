package com.example.coffeeapp.di

import androidx.lifecycle.ViewModel
import com.example.coffeeapp.presentation.login.LoginActivity
import com.example.coffeeapp.presentation.login.LoginFragment
import com.example.coffeeapp.presentation.login.LoginViewModel
import com.example.coffeeapp.presentation.login.RegistrationFragment
import dagger.Binds
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@ActivityScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create():LoginComponent
    }

    fun inject(activity: LoginActivity)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegistrationFragment)

//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginViewModel::class)
//    fun bindViewModel(viewModel: LoginViewModel): ViewModel
}