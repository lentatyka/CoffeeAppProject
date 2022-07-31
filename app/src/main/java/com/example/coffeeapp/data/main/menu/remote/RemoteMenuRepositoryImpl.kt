package com.example.coffeeapp.data.main.menu.remote

import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.di.main.FakeMenuServiceApi
import com.example.coffeeapp.domain.main.menu.remote.RemoteMenuRepository
import javax.inject.Inject

class RemoteMenuRepositoryImpl @Inject constructor(
    @FakeMenuServiceApi private val menuServiceApi: MenuServiceApi
) : RemoteMenuRepository {

    private lateinit var listMenu: ArrayList<MenuItem>

    override suspend fun loadMenu(id: Long) {
        listMenu = menuServiceApi(id)
    }

    override fun getMenu(): ArrayList<MenuItem> = listMenu

}