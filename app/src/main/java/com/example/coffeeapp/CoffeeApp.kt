package com.example.coffeeapp

import android.app.Application
import com.example.coffeeapp.di.AppComponent
import com.example.coffeeapp.di.DaggerAppComponent

open class CoffeeApp:Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(this)
    }
    //Если не получиться в мейн активити организовать checkTokenLifeTime. Тестим тут
}