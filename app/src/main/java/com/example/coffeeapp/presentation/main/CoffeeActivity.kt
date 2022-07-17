package com.example.coffeeapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.coffeeapp.CoffeeApp
import com.example.coffeeapp.R
import com.example.coffeeapp.di.main.MainComponent
import com.example.coffeeapp.di.main.MainScope

@MainScope
class CoffeeActivity : AppCompatActivity() {

    lateinit var mainComponent: MainComponent

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        mainComponent = (application as CoffeeApp).appComponent.mainComponent().create()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.coffee_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_av_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}