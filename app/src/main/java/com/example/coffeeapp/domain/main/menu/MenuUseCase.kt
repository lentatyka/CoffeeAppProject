package com.example.coffeeapp.domain.main.menu

import com.example.coffeeapp.common.State
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.domain.main.menu.local.LocalUseCase
import com.example.coffeeapp.domain.main.menu.remote.RemoteMenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuUseCase @Inject constructor(
    private val localUseCase: LocalUseCase,
    private val remoteRepository: RemoteMenuRepository
) {
    suspend fun loadMenu(id: Long?): Flow<State<ArrayList<MenuItem>>> {
        return flow {
            runCatching {
                remoteRepository(id)
            }.onSuccess { shopMenu ->
                localUseCase.setMenu(shopMenu)
                emit(State.Success(shopMenu))
            }.onFailure { error ->
                emit(State.Error(error.localizedMessage))
            }
        }
    }

    fun getMenu() = localUseCase.getMenu()

    suspend fun add(menuItem: MenuItem, ownerId: Int) =
        localUseCase.addOrderItem(menuItem, ownerId)

    suspend fun subtract(menuItem: MenuItem, ownerId: Int) =
        localUseCase.subtractOrderItem(menuItem, ownerId)
}