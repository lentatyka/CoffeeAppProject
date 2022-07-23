package com.example.coffeeapp.data.main.menu.local

import androidx.lifecycle.LiveData
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.domain.main.menu.local.StorageMenuRepository
import javax.inject.Inject

class MenuRepa @Inject constructor(
    private val menuDao: OrderDao
):StorageMenuRepository {
    override fun add(id: Int): Boolean {
        menuDao.incrementItemAmount(id)
        return true
    }

    override fun sub(id: Int): Boolean {
        menuDao.decrementItemAmount(id)
        return true
    }

    override fun getList() = menuDao.getMenuList()

    override fun setList(list: ArrayList<MenuItem>) {
        menuDao.insertMenuList(list)
    }

    override fun getTotal(): LiveData<Double> {
        TODO("Not yet implemented")
    }
}