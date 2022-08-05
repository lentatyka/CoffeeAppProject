package com.example.coffeeapp.data.main.menu.remote

import com.example.coffeeapp.di.main.menu.FakeMenuServiceApi
import com.example.coffeeapp.domain.main.menu.remote.RemoteMenuRepository
import javax.inject.Inject

class RemoteMenuRepositoryImpl @Inject constructor(
    @FakeMenuServiceApi private val menuServiceApi: MenuServiceApi
) : RemoteMenuRepository {

    private val menuListDto = mutableListOf<MenuItemDto>()

    override suspend fun loadMenu(id: Long) {
        menuListDto.clear()
        menuListDto.addAll(menuServiceApi(id))
    }

    override fun getMenu() = menuListDto

}