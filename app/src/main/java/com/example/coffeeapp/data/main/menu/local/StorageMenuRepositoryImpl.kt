package com.example.coffeeapp.data.main.menu.local

import com.example.coffeeapp.common.Constants.MAX_ITEMS
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.domain.main.menu.local.StorageMenuRepository
import javax.inject.Inject

class StorageMenuRepositoryImpl @Inject constructor(
) : StorageMenuRepository {

    private lateinit var shopMenuArray:ArrayList<MenuItem>

    override fun add(id: Int): Boolean {
        return shopMenuArray.find { it.id == id }?.let { item ->
            if (item.amount < MAX_ITEMS) {
                item.amount++
                true
            } else
                false
        } ?: false
    }

    override fun sub(id: Int): Boolean {
        return shopMenuArray.find { it.id == id }?.let { item ->
            if (item.amount > 0) {
                item.amount--
                true
            } else
                false
        } ?: false
    }

    override fun getList(): ArrayList<MenuItem> {
        return shopMenuArray
    }

    override fun setList(list: ArrayList<MenuItem>) {
        shopMenuArray = list
    }
}