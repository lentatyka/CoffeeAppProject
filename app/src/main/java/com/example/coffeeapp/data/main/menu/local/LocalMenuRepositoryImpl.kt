package com.example.coffeeapp.data.main.menu.local

import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.data.main.order.local.OrderDao
import com.example.coffeeapp.domain.main.menu.local.LocalMenuRepository
import javax.inject.Inject

class LocalMenuRepositoryImpl @Inject constructor(
    private val menuDao: MenuDao
) : LocalMenuRepository {

    override suspend fun add(id: Int){}

    override suspend fun sub(id: Int){}


    override fun getMenu() = menuDao.getMenu()

    override suspend fun setMenu(list: ArrayList<MenuItem>) = menuDao.insertMenu(list)
}