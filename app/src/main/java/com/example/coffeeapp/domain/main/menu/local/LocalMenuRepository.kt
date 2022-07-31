package com.example.coffeeapp.domain.main.menu.local

import com.example.coffeeapp.data.main.menu.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface LocalMenuRepository {

    fun getMenu(): Flow<List<MenuItem>>

    suspend fun setMenu(list: ArrayList<MenuItem>)
}