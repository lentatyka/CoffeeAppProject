package com.example.coffeeapp.presentation.main.screens.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.common.State
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.domain.main.menu.MenuUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val menuUseCase: MenuUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State<ArrayList<MenuItem>>>(State.Loading)
    val state: StateFlow<State<ArrayList<MenuItem>>> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            menuUseCase.loadMenu().onEach(_state::emit).collect()
        }
    }

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

    fun getMenu() = menuUseCase.getMenu()


    override fun onCleared() {
        super.onCleared()
        Log.d("TAG", "MENU ONCLEARED")
    }
}