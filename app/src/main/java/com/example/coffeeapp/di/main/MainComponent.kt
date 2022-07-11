package com.example.coffeeapp.di.main


import androidx.lifecycle.SavedStateHandle
import com.example.coffeeapp.presentation.main.CoffeeActivity
import com.example.coffeeapp.presentation.main.MainViewModelFactory
import com.example.coffeeapp.presentation.main.screens.menu.MenuFragment
import com.example.coffeeapp.presentation.main.screens.shops.ShopsFragment
import com.example.coffeeapp.presentation.main.screens.OrderFragment
import com.example.coffeeapp.presentation.main.screens.menu.MenuViewModel
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ShopModule::class,
        MenuModule::class,
        MainViewModelModule::class
    ]
)
@MainScope
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: CoffeeActivity)
    fun inject(fragment: ShopsFragment)
    fun inject(fragment: MenuFragment)
    fun inject(fragment: OrderFragment)

    fun viewModelFactory():MainViewModelFactory

    fun vmv():MenuViewModel.Factory


}