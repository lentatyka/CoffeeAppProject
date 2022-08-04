package com.example.coffeeapp.di.main.menu

import com.example.coffeeapp.presentation.main.screens.menu.MenuFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [MenuModule::class])
@MenuScope
interface MenuComponent {


    @Subcomponent.Factory
    interface Factory{
        fun create(@BindsInstance ownerId: Long):MenuComponent
    }

    fun inject(fragment: MenuFragment)

}