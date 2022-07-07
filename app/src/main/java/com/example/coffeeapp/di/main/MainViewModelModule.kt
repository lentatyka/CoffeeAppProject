package com.example.coffeeapp.di.main

import androidx.lifecycle.ViewModelProvider
import com.example.coffeeapp.presentation.main.MainViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface MainViewModelModule {

    @Binds
    fun bindMainViewModelFactory(factory: MainViewModelFactory):ViewModelProvider.Factory
}