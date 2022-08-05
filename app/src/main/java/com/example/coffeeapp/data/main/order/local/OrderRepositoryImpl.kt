package com.example.coffeeapp.data.main.order.local

import com.example.coffeeapp.domain.main.order.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao
) : OrderRepository {


    override suspend fun updateOrderAmount(id: Int, amount: Int, ownerId: Long) =
        orderDao.updateOrderAmount(
            id, amount, ownerId
        )

    override suspend fun deleteOrderItem(id: Int, ownerId: Long) = orderDao.deleteOrderItem(id, ownerId)

    override suspend fun deleteOrder(ownerId: Long) = orderDao.deleteOrder(ownerId)


    override fun getOrder(ownerId: Long) = orderDao.getOrder(ownerId)

    override fun getTotal(ownerId: Long) = orderDao.getTotal(ownerId)
}