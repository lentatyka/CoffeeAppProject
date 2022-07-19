package com.example.coffeeapp.presentation.main.screens.shops

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.domain.main.shops.model.Shop
import com.example.coffeeapp.domain.main.shops.ShopUseCase
import com.example.coffeeapp.domain.main.shops.location.LocationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopsViewModel @Inject constructor(
    private val shopsUseCase: ShopUseCase,
    private val locationUseCase: LocationUseCase
) : ViewModel() {

    private val _status = MutableLiveData<Resource<List<Shop>>>()
    val status: LiveData<Resource<List<Shop>>> = _status

    val location = shopsUseCase.getShopListLocation()

    init{
        viewModelScope.launch {
            shopsUseCase.loadShopList().collect {
                _status.postValue(it)
            }
        }
    }

    fun getShopList() = shopsUseCase.getShopList()

    fun startUpdateLocation() {
        _status.value?.let { status ->
            if (status is Resource.Success)
                locationUseCase.startUpdateLocation()
        }
    }

    fun stopUpdateLocation() {
        locationUseCase.stopUpdateLocation()
    }

    override fun onCleared() {
        Log.d("TAG", "SHOPVM CLEAR")
        stopUpdateLocation()
        super.onCleared()
    }
}