package com.example.coffeeapp.data.main.menu.remote

import com.example.coffeeapp.di.main.menu.FakeMenuServiceApi
import com.example.coffeeapp.domain.main.menu.remote.RemoteMenuRepository
import javax.inject.Inject

class RemoteMenuRepositoryImpl @Inject constructor(
    @FakeMenuServiceApi private val menuServiceApi: MenuServiceApi
) : RemoteMenuRepository {

    private lateinit var menuListDto: List<MenuItemDto>

    override suspend fun loadMenu(id: Long) {
        menuListDto = menuServiceApi(id)
    }

    override fun getMenu() = menuListDto

}