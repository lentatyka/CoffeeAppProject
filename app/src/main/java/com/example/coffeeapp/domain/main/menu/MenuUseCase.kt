package com.example.coffeeapp.domain.main.menu

import com.example.coffeeapp.common.State
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.domain.main.menu.local.StorageMenuRepository
import com.example.coffeeapp.domain.main.menu.remote.MenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuUseCase @Inject constructor(
    private val storageRepository: StorageMenuRepository,
    private val menuRepository: MenuRepository
) {
    suspend fun loadMenu(id: Long?): Flow<State<ArrayList<MenuItem>>> {
        return flow {
            runCatching {
                menuRepository(id)
            }.onSuccess {shopMenu ->
                emit(State.Success(shopMenu))
            }.onFailure {error->
                emit(State.Error(error.localizedMessage))
            }
        }
    }

    fun getMenu() = storageRepository.getMenu()

    suspend fun add(id: Int) = storageRepository.add(id)

    suspend fun sub(id: Int) = storageRepository.sub(id)
}