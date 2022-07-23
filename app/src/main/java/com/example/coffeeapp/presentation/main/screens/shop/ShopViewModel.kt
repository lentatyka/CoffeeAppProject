package com.example.coffeeapp.presentation.main.screens.shop

import androidx.lifecycle.*
import com.example.coffeeapp.common.State
import com.example.coffeeapp.domain.main.shop.ShopUseCase
import com.example.coffeeapp.domain.main.shop.location.LocationUseCase
import com.example.coffeeapp.domain.main.shop.model.Shop
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopViewModel @Inject constructor(
    private val shopsUseCase: ShopUseCase,
    private val locationUseCase: LocationUseCase
) : ViewModel() {

    private val _status = MutableLiveData<State>()
    val status: LiveData<State> = _status

    private var locationPermissionGranted = false

    private val _shopList =
        MutableSharedFlow<List<Shop>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val shopList: SharedFlow<List<Shop>> = _shopList.asSharedFlow()

    init {
        viewModelScope.launch {
            shopsUseCase.loadShopList().collect {
                _status.postValue(it)
            }
        }
    }

    fun startUpdateLocation() {
        _status.value?.let { state ->
            if (state is State.Success) {
                if (locationPermissionGranted) {
                    locationUseCase.startUpdateLocation()
                    getShopListLocation()
                } else
                    _shopList.tryEmit(shopsUseCase.getShopList())
            }

        }
    }

    fun stopUpdateLocation() {
        locationUseCase.stopUpdateLocation()
    }

    fun setLocationEnable(isEnabled: Boolean) {
        locationPermissionGranted = isEnabled
    }

    private fun getShopListLocation() {
        viewModelScope.launch {
            shopsUseCase.getShopListLocation().collectLatest {
                _shopList.emit(it)
            }
        }
    }

    override fun onCleared() {
        locationUseCase.stopUpdateLocation()
        super.onCleared()
    }


}