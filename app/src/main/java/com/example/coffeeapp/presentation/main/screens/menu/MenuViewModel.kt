package com.example.coffeeapp.presentation.main.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.domain.main.menu.MenuUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val menuUseCase: MenuUseCase
) : ViewModel() {

     val state  = menuUseCase.loadMenu().shareIn(
        viewModelScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )
    val menu = menuUseCase.getMenu().shareIn(
        viewModelScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )

    fun addAmount(menuItem: MenuItem) {
        viewModelScope.launch {
            menuUseCase.add(menuItem)
        }
    }

    fun subAmount(menuItem: MenuItem) {
        viewModelScope.launch {
            menuUseCase.subtract(menuItem)
        }
    }
}