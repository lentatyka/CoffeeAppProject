package com.example.coffeeapp.presentation.main.screens.order


import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.coffeeapp.common.State
import com.example.coffeeapp.domain.main.order.GetOrderUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class OrderViewModel @Inject constructor(
    private val getOrderUseCase: GetOrderUseCase
):ViewModel() {

    private val _state = MutableSharedFlow<State>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val state: SharedFlow<State> = _state.asSharedFlow()

    fun addAmount(id: Int) = getOrderUseCase.add(id)

    fun subAmount(id: Int) = getOrderUseCase.sub(id)

    fun getList() = getOrderUseCase.getList()

    fun getTotal() = getOrderUseCase.getTotal()

    fun checkOrder() = View.OnClickListener{
        if (getOrderUseCase.isEmptyOrder()){
            _state.tryEmit(State.Error(null))
        }else
            _state.tryEmit(State.Success)
    }

    override fun onCleared() {
        Log.d("TAG", "ORDER VM CLEAR")
        super.onCleared()
    }
}