package com.example.coffeeapp.di.main

import com.example.coffeeapp.presentation.main.CoffeeActivity
import com.example.coffeeapp.presentation.main.screens.MenuFragment
import com.example.coffeeapp.presentation.main.screens.ShopsFragment
import com.example.coffeeapp.presentation.main.screens.OrderFragment
import dagger.Subcomponent

@Subcomponent()
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: CoffeeActivity)
    fun inject(fragment: ShopsFragment)
    fun inject(fragment: MenuFragment)
    fun inject(fragment: OrderFragment)

}