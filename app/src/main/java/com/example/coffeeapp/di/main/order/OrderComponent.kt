package com.example.coffeeapp.di.main.order

import com.example.coffeeapp.presentation.main.screens.order.OrderFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [OrderModule::class])
@OrderScope
interface OrderComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(@BindsInstance ownerId: Long):OrderComponent
    }

    fun inject(fragment: OrderFragment)
}