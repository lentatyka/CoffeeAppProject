package com.example.coffeeapp.presentation.main.screens.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.domain.main.order.GetOrderUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrderViewModel @Inject constructor(
    private val getOrderUseCase: GetOrderUseCase
) : ViewModel() {

    fun addAmount(id: Int, amount: Int) {
        viewModelScope.launch {
            getOrderUseCase.add(id, amount)
        }
    }

    fun subtractAmount(id: Int, amount: Int) {
        viewModelScope.launch {
            getOrderUseCase.subtract(id, amount)
        }
    }

    fun getOrder() = getOrderUseCase.getOrder()

    fun getTotal() = getOrderUseCase.getTotal()

    fun deleteOrder(){
        viewModelScope.launch {
            getOrderUseCase.deleteOrder()
        }
    }
}