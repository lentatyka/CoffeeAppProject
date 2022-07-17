package com.example.coffeeapp.presentation.main.screens.order

import androidx.lifecycle.ViewModel
import com.example.coffeeapp.domain.main.menu.UserCase
import javax.inject.Inject

class OrderViewModel @Inject constructor(
    private val useCase: UserCase
):ViewModel() {

    fun addAmount(id: Int) = useCase.add(id)

    fun subAmount(id: Int) = useCase.sub(id)

    fun getList() = useCase.getList()
}