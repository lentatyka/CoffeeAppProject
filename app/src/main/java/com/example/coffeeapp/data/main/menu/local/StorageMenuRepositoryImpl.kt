package com.example.coffeeapp.data.main.menu.local

import androidx.lifecycle.LiveData
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.domain.main.menu.local.StorageMenuRepository
import javax.inject.Inject

class StorageMenuRepositoryImpl @Inject constructor(
    private val menuDao: OrderDao
) : StorageMenuRepository {

    override suspend fun add(id: Int) = menuDao.incrementItemAmount(id)

    override suspend fun sub(id: Int) = menuDao.decrementItemAmount(id)


    override fun getMenu() = menuDao.getMenuList()

    override suspend fun setMenu(list: ArrayList<MenuItem>) = menuDao.updateMenuList(list)
}