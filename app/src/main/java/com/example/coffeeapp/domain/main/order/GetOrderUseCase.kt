package com.example.coffeeapp.domain.main.order

import com.example.coffeeapp.domain.main.menu.local.StorageMenuRepository
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val storageMenuRepository: StorageMenuRepository
) {
    fun getList() = storageMenuRepository.getMenu()

    fun add(id: Int){}

    fun sub(id: Int){}

    fun isEmptyOrder() = false
}