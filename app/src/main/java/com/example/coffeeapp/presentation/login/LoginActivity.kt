package com.example.coffeeapp.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coffeeapp.CoffeeApp
import com.example.coffeeapp.R
import com.example.coffeeapp.di.login.LoginComponent
import com.example.coffeeapp.presentation.ViewModelFactory
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    lateinit var loginComponent: LoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        loginComponent = (application as CoffeeApp).appComponent.loginComponent().create()
        loginComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }
}