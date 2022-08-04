package com.example.coffeeapp.di.main


import com.example.coffeeapp.di.main.menu.MenuModule
import com.example.coffeeapp.di.main.order.OrderModule
import com.example.coffeeapp.di.main.shop.ShopModule
import com.example.coffeeapp.presentation.main.screens.menu.MenuFragment
import com.example.coffeeapp.presentation.main.screens.order.OrderFragment
import com.example.coffeeapp.presentation.main.screens.shop.ShopFragment
import dagger.Subcomponent

//@Subcomponent(
//    modules = [
//        ShopModule::class,
//        MenuModule::class,
//        OrderModule::class,
//        RoomModule::class
//    ]
//)
//@MainScope
//interface MainComponent {
//
//    @Subcomponent.Factory
//    interface Factory {
//        fun create(): MainComponent
//    }
//
//    fun inject(fragment: ShopFragment)
//    fun inject(fragment: MenuFragment)
//    fun inject(fragment: OrderFragment)
//
//}