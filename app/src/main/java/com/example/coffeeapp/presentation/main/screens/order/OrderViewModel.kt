package com.example.coffeeapp.presentation.main.screens.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.domain.main.order.GetOrderUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrderViewModel @Inject constructor(
    private val getOrderUseCase: GetOrderUseCase
):ViewModel() {

    fun addAmount(id: Int) {
//        viewModelScope.launch {
//            getOrderUseCase.add(id)
//        }
    }

    fun subtractAmount(id: Int) {
//        viewModelScope.launch {
//            getOrderUseCase.subtract(id)
//        }
    }

    fun getOrder() = getOrderUseCase.getOrder(2)

    fun getTotal() = getOrderUseCase.getTotal()
}