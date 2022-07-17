package com.example.coffeeapp.domain.main.menu.local

import com.example.coffeeapp.data.main.menu.model.MenuItem
import javax.inject.Inject

class GetStorageMenuUseCase @Inject constructor(
    private val storageMenuRepository: StorageMenuRepository
) {
    fun getList() = storageMenuRepository.getList()

    fun setList(list: ArrayList<MenuItem>) = storageMenuRepository.setList(list)

    fun add(id: Int) = storageMenuRepository.add(id)

    fun sub(id: Int) = storageMenuRepository.sub(id)
}