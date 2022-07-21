package com.example.coffeeapp.presentation.main.screens.shops

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.common.State
import com.example.coffeeapp.domain.main.shops.model.Shop
import com.example.coffeeapp.domain.main.shops.ShopUseCase
import com.example.coffeeapp.domain.main.shops.location.LocationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopViewModel @Inject constructor(
    private val shopsUseCase: ShopUseCase,
    private val locationUseCase: LocationUseCase
) : ViewModel() {

    private val _status = MutableLiveData<State>()
    val status: LiveData<State> = _status

    private var locationPermissionGranted = false

    init {
        viewModelScope.launch {
            shopsUseCase.loadShopList().collect {
                _status.postValue(it)
            }
        }
    }

    fun startUpdateLocation() {
        _status.value?.let { state->
            if(state is State.Success && locationPermissionGranted)
                locationUseCase.startUpdateLocation()
        }
    }

    fun stopUpdateLocation() {
        locationUseCase.stopUpdateLocation()
    }

    fun setLocationEnable(isEnabled: Boolean){
        locationPermissionGranted = isEnabled
    }

    fun getShopList() = shopsUseCase.getShopList()

    fun getShopListLocation() = shopsUseCase.getShopListLocation()

    override fun onCleared() {
        Log.d("TAG", "SHOPVM CLEAR")
        locationUseCase.stopUpdateLocation()
        super.onCleared()
    }


}