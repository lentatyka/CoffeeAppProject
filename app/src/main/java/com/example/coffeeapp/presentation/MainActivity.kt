package com.example.coffeeapp.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeapp.CoffeeApp
import com.example.coffeeapp.R
import com.example.coffeeapp.data.storage.LoadSharedPreferencesStorage
import com.example.coffeeapp.presentation.login.LoginActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sessionManager: LoadSharedPreferencesStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as CoffeeApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        val user = sessionManager()
        val currentTime = System.currentTimeMillis()

        if (user.token != null && user.tokenLifeTime > currentTime)
            launchActivity(MainActivity::class.java)
        else
            launchActivity(LoginActivity::class.java)
    }

    private fun <T : Activity> launchActivity(activity: Class<T>) {
        Intent(this, activity).also { intent ->
            intent.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
            )
            startActivity(intent)
            finish()
        }
    }
}