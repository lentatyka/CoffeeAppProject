package com.example.coffeeapp.presentation.main.screens.shops

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.common.Event
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.shops.ShopLocationDto
import com.example.coffeeapp.domain.main.shops.ShopsLocationUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopsViewModel @Inject constructor(
    private val shopsLocationUseCase: ShopsLocationUseCase
):ViewModel() {

    private val _status = MutableLiveData<Resource<List<ShopLocationDto>>>()
    val status: LiveData<Resource<List<ShopLocationDto>>> = _status

    init {
        viewModelScope.launch {
            shopsLocationUseCase().collect {
                _status.postValue(it)
            }
        }
    }
}