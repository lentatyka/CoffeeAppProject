package com.example.coffeeapp.presentation.main.screens.menu

import androidx.lifecycle.*
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.menu.ShopMenu
import com.example.coffeeapp.domain.main.menu.GetMenuUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getMenuUseCase: GetMenuUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _status = MutableLiveData<Resource<ArrayList<ShopMenu>>>()
    val status: LiveData<Resource<ArrayList<ShopMenu>>> = _status

    init {
        val shopId = savedStateHandle.get<Int>("shopId")
        viewModelScope.launch {
                getMenuUseCase(shopId).onEach { result ->
                    _status.postValue(result)
                }.collect()

        }
    }
}