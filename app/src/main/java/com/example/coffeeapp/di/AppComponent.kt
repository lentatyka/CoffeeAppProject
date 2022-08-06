package com.example.coffeeapp.di

import android.app.Application
import com.example.coffeeapp.di.login.LoginComponent
import com.example.coffeeapp.di.main.RoomModule
import com.example.coffeeapp.di.main.map.MapComponent
import com.example.coffeeapp.di.main.menu.MenuComponent
import com.example.coffeeapp.di.main.order.OrderComponent
import com.example.coffeeapp.di.main.shop.ShopComponent
import com.example.coffeeapp.di.retrifit.RetrofitModule
import com.example.coffeeapp.di.storage.StorageModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppSubComponent::class,
        StorageModule::class,
        RetrofitModule::class,
        RoomModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    fun loginComponent(): LoginComponent.Factory

    val shopComponent: ShopComponent.Factory

    val menuComponent: MenuComponent.Factory

    val orderComponent: OrderComponent.Factory

    val mapComponent: MapComponent.Factory

}