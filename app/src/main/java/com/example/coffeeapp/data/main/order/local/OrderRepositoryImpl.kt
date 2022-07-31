package com.example.coffeeapp.data.main.order.local

import com.example.coffeeapp.data.main.order.model.OrderItem
import com.example.coffeeapp.domain.main.order.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao
) : OrderRepository {

    override suspend fun addOrderItem(orderItem: OrderItem) = orderDao.addOrderItem(orderItem)

    override suspend fun deleteOrderItem(orderItem: OrderItem) = orderDao.deleteOrderItem(orderItem)

    override suspend fun deleteOrderItem(id: Int, ownerId: Int) =
        orderDao.deleteOrderItem(id, ownerId)

    override suspend fun deleteOrder(ownerId: Int) = orderDao.deleteOrder(ownerId)


    override fun getOrder(ownerId: Int) = orderDao.getOrder(ownerId)

    override fun getTotal() = orderDao.getTotal()
}