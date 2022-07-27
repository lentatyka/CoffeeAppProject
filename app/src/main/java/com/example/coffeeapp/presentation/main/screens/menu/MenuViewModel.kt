package com.example.coffeeapp.presentation.main.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.common.State
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.domain.main.menu.MenuUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MenuViewModel @AssistedInject constructor(
    private val useCase: MenuUseCase,
    @Assisted shopId: Long
) : ViewModel() {

    private val _state = MutableStateFlow<State<ArrayList<MenuItem>>>(State.Loading)
    val state: StateFlow<State<ArrayList<MenuItem>>> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            useCase.loadMenu(shopId).onEach(_state::emit).collect()
        }
    }

    fun addAmount(id: Int) {
        viewModelScope.launch {
            useCase.add(id)
        }
    }

    fun subAmount(id: Int) {
        viewModelScope.launch {
            useCase.sub(id)
        }
    }

    fun getList() = useCase.getMenu()

    @AssistedFactory
    interface Factory {
        fun create(shopId: Long): MenuViewModel
    }
}