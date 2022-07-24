package com.example.coffeeapp.domain.main.order

import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    fun getOrder() = orderRepository.getOrder()

    fun getTotal() = orderRepository.getTotal()

    suspend fun add(id: Int) = orderRepository.add(id)

    suspend fun sub(id: Int) = orderRepository.sub(id)

}