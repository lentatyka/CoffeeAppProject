package com.example.coffeeapp

import android.app.Application
import com.example.coffeeapp.di.AppComponent
import com.example.coffeeapp.di.DaggerAppComponent
import com.yandex.mapkit.MapKitFactory

open class CoffeeApp:Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.YANDEX_API_KEY)
    }
    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(this)
    }
    //Если не получиться в мейн активити организовать checkTokenLifeTime. Тестим тут
}