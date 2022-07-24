package com.example.coffeeapp.data.main.order

import com.example.coffeeapp.data.main.menu.local.OrderDao
import com.example.coffeeapp.domain.main.order.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao
):OrderRepository {

    override suspend fun add(id: Int) =orderDao.incrementItemAmount(id)

    override suspend fun sub(id: Int) = orderDao.decrementItemAmount(id)

    override fun getOrder() = orderDao.getOrder()

    override fun getTotal()=orderDao.getTotal()
}