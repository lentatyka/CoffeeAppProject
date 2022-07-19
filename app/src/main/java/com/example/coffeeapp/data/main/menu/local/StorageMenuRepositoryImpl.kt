package com.example.coffeeapp.data.main.menu.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coffeeapp.common.Constants.MAX_ITEMS
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.di.main.MainScope
import com.example.coffeeapp.domain.main.menu.local.StorageMenuRepository
import javax.inject.Inject

@MainScope
class StorageMenuRepositoryImpl @Inject constructor(
) : StorageMenuRepository {

    private lateinit var shopMenuArray: ArrayList<MenuItem>

    private lateinit var total: MutableLiveData<Double>

    override fun add(id: Int): Boolean {
        return shopMenuArray.find { it.id == id }?.let { item ->
            if (item.amount < MAX_ITEMS) {
                item.amount++
                total.value = total.value!! + item.price
                true
            } else
                false
        } ?: false
    }

    override fun sub(id: Int): Boolean {
        return shopMenuArray.find { it.id == id }?.let { item ->
            if (item.amount > 0) {
                item.amount--
                total.value = total.value!! - item.price
                true
            } else
                false
        } ?: false
    }

    override fun getList(): ArrayList<MenuItem> {
        return shopMenuArray
    }

    override fun setList(list: ArrayList<MenuItem>) {
        total = MutableLiveData(0.0)
        shopMenuArray = list
    }

    override fun getTotal(): LiveData<Double> = total
}