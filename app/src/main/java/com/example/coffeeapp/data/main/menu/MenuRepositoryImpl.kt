package com.example.coffeeapp.data.main.menu

import com.example.coffeeapp.di.main.FakeMenuServiceApi
import com.example.coffeeapp.domain.main.menu.MenuRepository
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    @FakeMenuServiceApi private val menuServiceApi: MenuServiceApi
):MenuRepository {

    override suspend fun invoke(id: Int?) = menuServiceApi.invoke(id)
}