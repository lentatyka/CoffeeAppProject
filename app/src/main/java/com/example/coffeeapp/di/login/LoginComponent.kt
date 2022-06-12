package com.example.coffeeapp.di.login

import com.example.coffeeapp.presentation.login.LoginActivity
import com.example.coffeeapp.presentation.login.LoginFragment
import com.example.coffeeapp.presentation.login.RegistrationFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): LoginComponent
    }

    fun inject(activity: LoginActivity)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegistrationFragment)


}