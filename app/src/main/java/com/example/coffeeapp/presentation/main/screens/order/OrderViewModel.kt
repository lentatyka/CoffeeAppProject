package com.example.coffeeapp.presentation.main.screens.order


import androidx.lifecycle.ViewModel
import com.example.coffeeapp.domain.main.order.GetOrderUseCase
import javax.inject.Inject

class OrderViewModel @Inject constructor(
    private val getOrderUseCase: GetOrderUseCase
):ViewModel() {

    fun addAmount(id: Int) = getOrderUseCase.add(id)

    fun subAmount(id: Int) = getOrderUseCase.sub(id)

    fun getList() = getOrderUseCase.getList()

    fun getTotal() = getOrderUseCase.getTotal()
}