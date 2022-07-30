package com.example.coffeeapp.domain.main.menu

import com.example.coffeeapp.common.State
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.domain.main.menu.local.LocalMenuRepository
import com.example.coffeeapp.domain.main.menu.remote.RemoteMenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuUseCase @Inject constructor(
    private val localRepository: LocalMenuRepository,
    private val remoteRepository: RemoteMenuRepository
) {
    suspend fun loadMenu(id: Long?): Flow<State<ArrayList<MenuItem>>> {
        return flow {
            runCatching {
                remoteRepository(id)
            }.onSuccess {shopMenu ->
                localRepository.setMenu(shopMenu)
                emit(State.Success(shopMenu))
            }.onFailure {error->
                emit(State.Error(error.localizedMessage))
            }
        }
    }

    fun getMenu() = localRepository.getMenu()

    suspend fun add(id: Int) = localRepository.add(id)

    suspend fun sub(id: Int) = localRepository.sub(id)
}