package com.example.coffeeapp.domain.main.menu.local

import com.example.coffeeapp.data.main.order.model.OrderItemDto
import kotlinx.coroutines.flow.Flow

interface LocalMenuRepository {

    suspend fun insertOrder(orderItemDto: OrderItemDto)

    suspend fun deleteOrder(id: Int, ownerId: Long)

    fun getOrders(ownerId: Long): Flow<List<OrderItemDto>>
}