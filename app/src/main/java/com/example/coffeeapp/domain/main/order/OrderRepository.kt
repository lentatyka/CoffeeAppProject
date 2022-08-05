package com.example.coffeeapp.domain.main.order

import com.example.coffeeapp.data.main.order.model.OrderItemDto
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    suspend fun updateOrderAmount(id: Int, amount:Int, ownerId: Long)

    suspend fun deleteOrderItem(id: Int, ownerId: Long)

    suspend fun deleteOrder(ownerId: Long)

    fun getOrder(ownerId: Long):Flow<List<OrderItemDto>>

    fun getTotal(ownerId: Long):Flow<Double>
}