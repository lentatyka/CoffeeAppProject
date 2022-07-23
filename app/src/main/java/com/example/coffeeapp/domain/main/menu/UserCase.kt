package com.example.coffeeapp.domain.main.menu

import com.example.coffeeapp.common.State
import com.example.coffeeapp.domain.main.menu.local.StorageMenuRepository
import com.example.coffeeapp.domain.main.menu.remote.MenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserCase @Inject constructor(
    private val storageRepository: StorageMenuRepository,
    private val menuRepository: MenuRepository
) {
    suspend fun loadMenu(id: Long?): Flow<State> {
        return flow {
            emit(State.Loading)
            runCatching {
                menuRepository(id)
            }.onSuccess {shopMenu ->
                storageRepository.setList(shopMenu)
                emit(State.Success)
            }.onFailure {error->
                emit(State.Error(error.localizedMessage))
            }
        }
    }

    fun getList() = storageRepository.getList()

    fun add(id: Int) = storageRepository.add(id)

    fun sub(id: Int) = storageRepository.sub(id)
}