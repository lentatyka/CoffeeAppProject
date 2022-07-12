package com.example.coffeeapp.presentation.main.screens.menu

import android.util.Log
import androidx.lifecycle.*
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.menu.ShopMenu
import com.example.coffeeapp.domain.main.menu.GetMenuUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MenuViewModel @AssistedInject constructor(
    private val getMenuUseCase: GetMenuUseCase,
    @Assisted shopId: Long
) : ViewModel() {

    private val _status = MutableLiveData<Resource<ArrayList<ShopMenu>>>()
    val status: LiveData<Resource<ArrayList<ShopMenu>>> = _status

    init {
        viewModelScope.launch {
            getMenuUseCase(shopId).onEach { result ->
                _status.postValue(result)
            }.collect()

        }
    }

    @AssistedFactory
    interface Factory {
        fun create(shopId: Long): MenuViewModel
    }

    override fun onCleared() {
        Log.d("TAG", "MENU CLEARED")
        super.onCleared()
    }
}