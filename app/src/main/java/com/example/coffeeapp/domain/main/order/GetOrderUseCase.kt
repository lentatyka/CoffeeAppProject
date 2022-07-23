package com.example.coffeeapp.domain.main.order

import com.example.coffeeapp.domain.main.menu.local.StorageMenuRepository
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val storageMenuRepository: StorageMenuRepository
) {
    fun getList() = storageMenuRepository.getList()

    fun add(id: Int) = storageMenuRepository.add(id)

    fun sub(id: Int) = storageMenuRepository.sub(id)

    fun getTotal() = storageMenuRepository.getTotal()

    fun isEmptyOrder() = false
}