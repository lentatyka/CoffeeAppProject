package com.example.coffeeapp.domain.main.menu.remote

import com.example.coffeeapp.data.main.menu.remote.MenuItemDto

interface RemoteMenuRepository {

    suspend fun loadMenu(id: Long)

    fun getMenu():List<MenuItemDto>
}