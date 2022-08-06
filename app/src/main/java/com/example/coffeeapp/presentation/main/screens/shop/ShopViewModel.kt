package com.example.coffeeapp.presentation.main.screens.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.common.State
import com.example.coffeeapp.domain.main.shop.ShopUseCase
import com.example.coffeeapp.domain.main.shop.location.LocationUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ShopViewModel @Inject constructor(
    shopsUseCase: ShopUseCase,
    private val locationUseCase: LocationUseCase
) : ViewModel() {

    val state = shopsUseCase.loadShopList().stateIn(
        scope = viewModelScope,
        initialValue = State.Loading,
        started = SharingStarted.WhileSubscribed()
    )

    val shops = shopsUseCase.getShopListLocation().shareIn(
        scope = viewModelScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )

    private var locationPermissionGranted = false

    fun startUpdateLocation() {
        if (locationPermissionGranted)
            locationUseCase.startUpdateLocation()
    }

    fun stopUpdateLocation() {
        locationUseCase.stopUpdateLocation()
    }

    fun setLocationEnable(isEnabled: Boolean) {
        locationPermissionGranted = isEnabled
    }

    override fun onCleared() {
        locationUseCase.stopUpdateLocation()
        super.onCleared()
    }


}