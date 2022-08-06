package com.example.coffeeapp.di

import com.example.coffeeapp.di.login.LoginComponent
import com.example.coffeeapp.di.main.map.MapComponent
import com.example.coffeeapp.di.main.menu.MenuComponent
import com.example.coffeeapp.di.main.order.OrderComponent
import com.example.coffeeapp.di.main.shop.ShopComponent
import dagger.Module

@Module(
    subcomponents = [
        LoginComponent::class,
        ShopComponent::class,
        MenuComponent::class,
        OrderComponent::class,
        MapComponent::class
    ]
)
class AppSubComponent