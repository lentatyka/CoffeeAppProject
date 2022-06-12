package com.example.coffeeapp.di

import android.app.Application
import com.example.coffeeapp.di.login.LoginComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppSubComponent::class,
        ViewModelFactoryModule::class,
        StorageModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    fun loginComponent(): LoginComponent.Factory
}