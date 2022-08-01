package com.example.coffeeapp.presentation.main.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

/*
Too long way to create Factory with parameters :D
 */
//class MenuViewModelFactory @AssistedInject constructor(
//    private val factory: MenuViewModel.Factory,
//    @Assisted private val shopId: Long
//) : ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return factory.create(shopId) as T
//    }
//
//    @AssistedFactory
//    interface Factory {
//        fun create(shopId: Long): MenuViewModelFactory
//    }
//}