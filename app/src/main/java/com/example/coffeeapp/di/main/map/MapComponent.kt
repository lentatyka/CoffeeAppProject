package com.example.coffeeapp.di.main.map

import com.example.coffeeapp.di.main.shop.ShopModule
import com.example.coffeeapp.di.main.shop.ShopScope
import com.example.coffeeapp.presentation.main.screens.map.YandexMapFragment
import dagger.Subcomponent

@Subcomponent(modules = [ShopModule::class])
@ShopScope
interface MapComponent {


    @Subcomponent.Factory
    interface Factory {
        fun create(): MapComponent
    }

    fun inject(fragment: YandexMapFragment)
}
