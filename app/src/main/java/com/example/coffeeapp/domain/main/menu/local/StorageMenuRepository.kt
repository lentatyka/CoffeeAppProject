package com.example.coffeeapp.domain.main.menu.local

import androidx.lifecycle.LiveData
import com.example.coffeeapp.data.main.menu.model.MenuItem

interface StorageMenuRepository {

    fun add(id: Int):Boolean

    fun sub(id: Int):Boolean

    fun getList():ArrayList<MenuItem>

    fun setList(list: ArrayList<MenuItem>)

    fun getTotal():LiveData<Double>
}