package com.example.coffeeapp.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
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

    override fun onBackPressed() {
        for(fragment in supportFragmentManager.fragments){
            if(fragment is RegistrationFragment)
                supportFragmentManager.commit {
                    replace<LoginFragment>(R.id.nav_host_fragment)
                }
            else
                super.onBackPressed()
        }
    }
}