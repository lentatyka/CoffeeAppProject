package com.example.coffeeapp.domain.main.order

import com.example.coffeeapp.common.Constants
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
    private val ownerId: Long
) {
    fun getOrder() = orderRepository.getOrder(ownerId)

    fun getTotal() = orderRepository.getTotal(ownerId)

    suspend fun add(id: Int, amount: Int) {
        when (amount) {
            Constants.MAX_ITEMS -> return
            else -> orderRepository.updateOrderAmount(id, amount + 1, ownerId)
        }
    }

    suspend fun subtract(id: Int, amount: Int) {
        when (amount) {
            0 -> orderRepository.deleteOrderItem(id, ownerId)
            else -> orderRepository.updateOrderAmount(id, amount - 1, ownerId)
        }
    }

    suspend fun deleteOrder() = orderRepository.deleteOrder(ownerId)
}