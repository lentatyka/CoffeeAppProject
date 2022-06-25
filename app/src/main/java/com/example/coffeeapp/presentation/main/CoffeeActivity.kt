package com.example.coffeeapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.coffeeapp.CoffeeApp
import com.example.coffeeapp.R

class CoffeeActivity : AppCompatActivity() {

//    lateinit var coffeeComponent: CoffeeComponent

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

//        coffeeComponent = (application as CoffeeApp).appComponent.coffeeComponent().create()
//        coffeeComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.coffee_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//
//        navController = navHostFragment.navController
//
//        setupActionBarWithNavController(navController)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}