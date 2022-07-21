package com.example.coffeeapp.domain.main.menu

import com.example.coffeeapp.common.State
import com.example.coffeeapp.domain.main.menu.local.GetStorageMenuUseCase
import com.example.coffeeapp.domain.main.menu.remote.GetMenuUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserCase @Inject constructor(
    private val getMenuUseCase: GetMenuUseCase,
    private val getStorageMenuUseCase: GetStorageMenuUseCase
) {
    suspend fun loadMenu(id: Long?): Flow<State> {
        return flow {
            emit(State.Loading)
            runCatching {
                getMenuUseCase(id)
            }.onSuccess {shopMenu ->
                getStorageMenuUseCase.setList(shopMenu)
                emit(State.Success)
            }.onFailure {error->
                emit(State.Error(error.localizedMessage ?: "unknown error"))
            }
        }
    }

    fun getList() = getStorageMenuUseCase.getList()

    fun add(id: Int) = getStorageMenuUseCase.add(id)

    fun sub(id: Int) = getStorageMenuUseCase.sub(id)
}