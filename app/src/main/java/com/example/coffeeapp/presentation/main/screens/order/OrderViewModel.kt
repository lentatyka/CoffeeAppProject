package com.example.coffeeapp.presentation.main.screens.order

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
}