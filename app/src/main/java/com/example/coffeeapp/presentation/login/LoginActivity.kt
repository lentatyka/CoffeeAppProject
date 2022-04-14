package com.example.coffeeapp.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.coffeeapp.CoffeeApp
import com.example.coffeeapp.R
import com.example.coffeeapp.di.LoginComponent
import com.example.coffeeapp.presentation.ViewModelFactory
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var loginViewModel: LoginViewModel

    lateinit var loginComponent: LoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        loginComponent = (application as CoffeeApp).appComponent.loginComponent().create()
        loginComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        setViewModel()
    }

    private fun setViewModel() {
        loginViewModel = ViewModelProvider(
            this, viewModelFactory
        )[LoginViewModel::class.java]
    }
}