package com.example.coffeeapp.di.main


import com.example.coffeeapp.presentation.main.CoffeeActivity
import com.example.coffeeapp.presentation.main.screens.shops.ShopsViewModelFactory
import com.example.coffeeapp.presentation.main.screens.menu.MenuFragment
import com.example.coffeeapp.presentation.main.screens.shops.ShopsFragment
import com.example.coffeeapp.presentation.main.screens.order.OrderFragment
import com.example.coffeeapp.presentation.main.screens.menu.MenuViewModelFactory
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ShopModule::class,
        MenuModule::class,
        OrderModule::class
    ]
)
@MainScope
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

//    fun inject(activity: CoffeeActivity)
//    fun inject(fragment: ShopsFragment)
//    fun inject(fragment: MenuFragment)
//    fun inject(fragment: OrderFragment)

    fun shopsViewModelFactory(): ShopsViewModelFactory

    fun menuViewModelFactory(): MenuViewModelFactory.Factory

}