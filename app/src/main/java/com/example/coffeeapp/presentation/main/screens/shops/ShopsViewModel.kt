package com.example.coffeeapp.presentation.main.screens.shops

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.shops.location.LocationDataSource
import com.example.coffeeapp.data.main.shops.remote.ShopRepositoryImpl
import com.example.coffeeapp.data.main.shops.remote.ShopServiceApi
import com.example.coffeeapp.domain.main.shops.ShopLocation
import com.example.coffeeapp.domain.main.shops.ShopsLocationUseCase
import com.example.coffeeapp.domain.main.shops.ShopsUseCase
import com.example.coffeeapp.domain.main.shops.UserLocationUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopsViewModel @Inject constructor(
    private val shopsUseCase: ShopsUseCase
):ViewModel() {

    private val _status = MutableLiveData<Resource<List<ShopLocation>>>()
    val status: LiveData<Resource<List<ShopLocation>>> = _status


    fun onRequestPermissionResult(requestCode: Int, grantResults: IntArray){

    }

    fun tester(){
        viewModelScope.launch {
            Log.d("TAG", "LAUNCTH")
            shopsUseCase.zzz().collect {
                Log.d("TAG", "$it")
                if(it is Resource.Success)
                    Log.d("TAG", "${it.data}")
            }
//            shopsUseCase().onEach {
//                Log.d("TAG", "COLLECTOR $it")
//                if(it is Resource.Success)
//                    Log.d("TAG", "SSS ${it.data}")
////                _status.postValue(
////                    it
////                )
//            }.collect()
        }
    }

    override fun onCleared() {
        Log.d("TAG", "OInclear")
        super.onCleared()
    }
}