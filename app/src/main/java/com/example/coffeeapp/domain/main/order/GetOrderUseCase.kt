package com.example.coffeeapp.domain.main.order

import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
    private val ownerId: Long
) {
    fun getOrder(ownerId: Int) = orderRepository.getOrder(ownerId)

    fun getTotal() = orderRepository.getTotal(ownerId)

}