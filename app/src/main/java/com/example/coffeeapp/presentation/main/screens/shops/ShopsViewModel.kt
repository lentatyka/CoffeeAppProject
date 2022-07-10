package com.example.coffeeapp.presentation.main.screens.shops

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.domain.main.shops.ShopLocation
import com.example.coffeeapp.domain.main.shops.ShopsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopsViewModel @Inject constructor(
    private val shopsUseCase: ShopsUseCase
) : ViewModel() {

    private val _status = MutableLiveData<Resource<List<ShopLocation>>>()
    val status: LiveData<Resource<List<ShopLocation>>> = _status

    fun getShopList() {
        viewModelScope.launch {
            shopsUseCase.getShopsList().collect {
                _status.postValue(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}