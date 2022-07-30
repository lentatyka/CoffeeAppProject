package com.example.coffeeapp.data.main.menu.remote

import com.example.coffeeapp.di.main.FakeMenuServiceApi
import com.example.coffeeapp.domain.main.menu.remote.RemoteMenuRepository
import javax.inject.Inject

class RemoteMenuRepositoryImpl @Inject constructor(
    @FakeMenuServiceApi private val menuServiceApi: MenuServiceApi
): RemoteMenuRepository {

    override suspend fun invoke(id: Long?) = menuServiceApi.invoke(id)
}