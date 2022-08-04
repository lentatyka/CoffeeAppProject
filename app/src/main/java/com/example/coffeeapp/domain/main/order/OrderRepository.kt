package com.example.coffeeapp.domain.main.order

import com.example.coffeeapp.data.main.order.model.OrderItemDto
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    suspend fun addOrderItem(orderItemDto: OrderItemDto)

    suspend fun deleteOrderItem(orderItemDto: OrderItemDto)

    suspend fun deleteOrderItem(id: Int, ownerId: Long)

    suspend fun deleteOrder(ownerId: Int)

    fun getOrder(ownerId: Int): Flow<List<OrderItemDto>>

    fun getTotal(ownerId: Long): Flow<Double>

}