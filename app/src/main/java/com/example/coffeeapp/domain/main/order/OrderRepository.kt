package com.example.coffeeapp.domain.main.order

import com.example.coffeeapp.data.main.order.model.OrderItem
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    suspend fun addOrderItem(orderItem: OrderItem)

    suspend fun deleteOrderItem(orderItem: OrderItem)

    suspend fun deleteOrderItem(id: Int, ownerId: Int)

    suspend fun deleteOrder(ownerId: Int)

    fun getOrder(ownerId: Int): Flow<List<OrderItem>>

    fun getTotal(): Flow<Double>

}