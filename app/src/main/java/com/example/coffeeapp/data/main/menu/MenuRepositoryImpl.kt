package com.example.coffeeapp.data.main.menu

import com.example.coffeeapp.domain.main.menu.MenuRepository

class MenuRepositoryImpl(
    private val menuServiceApi: MenuServiceApi
):MenuRepository {

    override suspend fun invoke(id: Int) = menuServiceApi.getMenu(id)
}