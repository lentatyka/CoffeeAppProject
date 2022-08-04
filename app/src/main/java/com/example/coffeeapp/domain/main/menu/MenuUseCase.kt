package com.example.coffeeapp.domain.main.menu

import com.example.coffeeapp.common.Constants
import com.example.coffeeapp.common.State
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.data.main.order.model.OrderItemDto
import com.example.coffeeapp.domain.main.menu.local.LocalMenuRepository
import com.example.coffeeapp.domain.main.menu.remote.RemoteMenuRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MenuUseCase @Inject constructor(
    private val localMenuRepository: LocalMenuRepository,
    private val remoteRepository: RemoteMenuRepository,
    private val ownerId: Long
) {
    suspend fun loadMenu(): Flow<State<ArrayList<MenuItem>>> {
        return flow {
            runCatching {
                remoteRepository.loadMenu(ownerId)
            }.onSuccess { menu ->
                emit(State.Success(null))
            }.onFailure { error ->
                emit(State.Error(error.localizedMessage))
            }
        }
    }

    fun getMenu(): Flow<List<MenuItem>> {
        return localMenuRepository.getOrders(ownerId).map { orders ->
            val list = mutableListOf<MenuItem>()
            remoteRepository.getMenu().onEach { menuItemDto ->
                list += MenuItem(
                    id = menuItemDto.id,
                    name = menuItemDto.name,
                    price = menuItemDto.price,
                    imageUrl = menuItemDto.imageUrl,
                    amount = orders.find { it.id == menuItemDto.id }?.amount ?: 0
                )
            }
            list
        }
    }

    suspend fun add(menuItem: MenuItem) {
        when (menuItem.amount) {
            Constants.MAX_ITEMS -> return
            else -> localMenuRepository.insertOrder(
                OrderItemDto(
                    id = menuItem.id,
                    name = menuItem.name,
                    price = menuItem.price,
                    amount = menuItem.amount+1,
                    ownerId = this.ownerId
                )
            )
        }
    }

    suspend fun subtract(menuItem: MenuItem) {
        when (menuItem.amount) {
            0 -> {
                val item = OrderItemDto(
                    id = menuItem.id,
                    name = menuItem.name,
                    price = menuItem.price,
                    amount = menuItem.amount,
                    ownerId = this.ownerId
                )
                localMenuRepository.deleteOrder(
                    item
                )
            }
            else -> {
                val item = OrderItemDto(
                    id = menuItem.id,
                    name = menuItem.name,
                    price = menuItem.price,
                    amount = menuItem.amount-1,
                    ownerId = this.ownerId
                )
                localMenuRepository.insertOrder(
                    item
                )
            }
        }
    }
}