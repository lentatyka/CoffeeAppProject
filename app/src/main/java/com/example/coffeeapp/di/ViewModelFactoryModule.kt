package com.example.coffeeapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.coffeeapp.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindviewModelFactory(vmFactory: ViewModelFactory): ViewModelProvider.Factory
}