package com.example.coffeeapp.presentation.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.coffeeapp.CoffeeApp
import com.example.coffeeapp.R
import com.example.coffeeapp.di.login.LoginComponent
import com.example.coffeeapp.di.login.LoginScope
import javax.inject.Inject

@LoginScope
class LoginActivity : AppCompatActivity() {

    private lateinit var loginComponent: LoginComponent

    @Inject
    lateinit var viewModelFactory:LoginViewModelFactory

    val loginViewModel by viewModels<LoginViewModel> {
        viewModelFactory
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        loginComponent = (application as CoffeeApp).appComponent.loginComponent().create()
        loginComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}