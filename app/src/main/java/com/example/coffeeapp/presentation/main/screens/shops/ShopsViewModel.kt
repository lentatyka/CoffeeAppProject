package com.example.coffeeapp.presentation.main.screens.shops

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.domain.main.shops.model.Shop
import com.example.coffeeapp.domain.main.shops.ShopUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopsViewModel @Inject constructor(
    private val shopsUseCase: ShopUseCase
) : ViewModel() {

    private val _status = MutableLiveData<Resource<List<Shop>>>()
    val status: LiveData<Resource<List<Shop>>> = _status

    fun getShopList() {
        viewModelScope.launch {
            shopsUseCase.getShopsList().collect {
                _status.postValue(it)
            }
        }
    }

    override fun onCleared() {
        Log.d("TAG", "SHOPVM CLEAR")
        super.onCleared()
    }
}