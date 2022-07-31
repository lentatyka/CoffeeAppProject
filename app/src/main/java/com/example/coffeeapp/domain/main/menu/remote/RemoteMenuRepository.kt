package com.example.coffeeapp.domain.main.menu.remote

import com.example.coffeeapp.data.main.menu.model.MenuItem

interface RemoteMenuRepository {

    suspend fun loadMenu(id: Long)

    fun getMenu():ArrayList<MenuItem>
}