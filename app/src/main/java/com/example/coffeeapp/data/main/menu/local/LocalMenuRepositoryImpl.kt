package com.example.coffeeapp.data.main.menu.local

import com.example.coffeeapp.data.main.order.model.OrderItemDto
import com.example.coffeeapp.domain.main.menu.local.LocalMenuRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMenuRepositoryImpl @Inject constructor(
    private val menuDao: MenuDao
) : LocalMenuRepository {
    override suspend fun insertOrder(orderItemDto: OrderItemDto) = menuDao.insertOrderItem(orderItemDto)

    override suspend fun deleteOrder(id:Int, ownerId: Long) =menuDao.deleteOrderItem(id, ownerId)

    override fun getOrders(ownerId: Long): Flow<List<OrderItemDto>> =menuDao.getOrders(ownerId)

}