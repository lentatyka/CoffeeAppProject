package com.example.coffeeapp.di.main.shop

import com.example.coffeeapp.presentation.main.screens.shop.ShopFragment
import dagger.Subcomponent

@Subcomponent(modules = [ShopModule::class])
@ShopScope
interface ShopComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): ShopComponent
    }

    fun inject(fragment: ShopFragment)
}