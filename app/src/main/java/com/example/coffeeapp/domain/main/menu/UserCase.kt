package com.example.coffeeapp.domain.main.menu

import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.domain.main.menu.local.GetStorageMenuUseCase
import com.example.coffeeapp.domain.main.menu.remote.GetMenuUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserCase @Inject constructor(
    private val getMenuUseCase: GetMenuUseCase,
    private val getStorageMenuUseCase: GetStorageMenuUseCase
) {
    suspend fun loadMenu(id: Long?): Flow<Resource<ArrayList<MenuItem>>> {
        return flow {
            emit(Resource.Loading)
            runCatching {
                getMenuUseCase(id)
            }.onSuccess {shopMenu ->
                getStorageMenuUseCase.setList(shopMenu)
                emit(Resource.Success(shopMenu))
            }.onFailure {error->
                emit(Resource.Error(error.localizedMessage ?: "unknown error"))
            }
        }
    }

    fun getList() = getStorageMenuUseCase.getList()

    fun add(id: Int) = getStorageMenuUseCase.add(id)

    fun sub(id: Int) = getStorageMenuUseCase.sub(id)
}