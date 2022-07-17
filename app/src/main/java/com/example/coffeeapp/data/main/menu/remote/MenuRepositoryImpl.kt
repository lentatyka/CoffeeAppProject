package com.example.coffeeapp.data.main.menu.remote

import com.example.coffeeapp.di.main.FakeMenuServiceApi
import com.example.coffeeapp.domain.main.menu.remote.MenuRepository
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    @FakeMenuServiceApi private val menuServiceApi: MenuServiceApi
): MenuRepository {

    override suspend fun invoke(id: Long?) = menuServiceApi.invoke(id)
}