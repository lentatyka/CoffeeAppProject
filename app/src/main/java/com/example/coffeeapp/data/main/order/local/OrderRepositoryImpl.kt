package com.example.coffeeapp.data.main.order.local

import com.example.coffeeapp.data.main.order.model.OrderItemDto
import com.example.coffeeapp.domain.main.order.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao
) : OrderRepository {

    override suspend fun addOrderItem(orderItemDto: OrderItemDto) = orderDao.addOrderItem(orderItemDto)

    override suspend fun deleteOrderItem(orderItemDto: OrderItemDto) = orderDao.deleteOrderItem(orderItemDto)

    override suspend fun deleteOrderItem(id: Int, ownerId: Long) =
        orderDao.deleteOrderItem(id, ownerId)

    override suspend fun deleteOrder(ownerId: Int) = orderDao.deleteOrder(ownerId)


    override fun getOrder(ownerId: Int) = orderDao.getOrder(ownerId)

    override fun getTotal(ownerId: Long) = orderDao.getTotal(ownerId)
}