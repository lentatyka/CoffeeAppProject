package com.example.coffeeapp.domain.main.menu.local


import com.example.coffeeapp.common.Constants
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.data.main.order.model.OrderItem
import com.example.coffeeapp.domain.main.order.OrderRepository
import javax.inject.Inject

class LocalUseCase @Inject constructor(
    private val localMenuRepository: LocalMenuRepository,
    private val orderRepository: OrderRepository
) {

    suspend fun setMenu(menuList: ArrayList<MenuItem>) = localMenuRepository.setMenu(menuList)

    fun getMenu() = localMenuRepository.getMenu()

    suspend fun addOrderItem(menuItem: MenuItem, ownerId: Long) {
        if (++menuItem.amount < Constants.MAX_ITEMS)
            insertOrderItem(menuItem, ownerId)
    }

    suspend fun subtractOrderItem(menuItem: MenuItem, ownerId: Long) {
        when(--menuItem.amount){
            0 -> deleteOrderItem(menuItem.id, ownerId)
            else -> insertOrderItem(menuItem, ownerId)
        }

    }

    private suspend fun insertOrderItem(menuItem: MenuItem, ownerId: Long) {
        OrderItem(
            id = menuItem.id,
            name = menuItem.name,
            price = menuItem.price,
            amount = menuItem.amount,
            ownerId = ownerId
        ).also {
            orderRepository.addOrderItem(it)
        }
    }

    private suspend fun deleteOrderItem(id: Int, ownerId: Long) =
        orderRepository.deleteOrderItem(id, ownerId)
}