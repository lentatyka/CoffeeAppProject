package com.example.coffeeapp.di.main


import com.example.coffeeapp.presentation.main.screens.menu.MenuFragment
import com.example.coffeeapp.presentation.main.screens.shop.ShopViewModelFactory
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
    fun inject(fragment: MenuFragment)
//    fun inject(fragment: OrderFragment)

    fun shopsViewModelFactory(): ShopViewModelFactory

}