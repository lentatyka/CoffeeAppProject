package com.example.coffeeapp.di.main

import androidx.lifecycle.ViewModel
import com.example.coffeeapp.data.main.order.local.OrderRepositoryImpl
import com.example.coffeeapp.di.ViewModelKey
import com.example.coffeeapp.domain.main.order.OrderRepository
import com.example.coffeeapp.presentation.main.screens.order.OrderViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class OrderModule {

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    abstract fun bindViewModel(viewModel: OrderViewModel):ViewModel

    @Binds
    abstract fun bindOrderRepository(repositoryImpl: OrderRepositoryImpl):OrderRepository
}