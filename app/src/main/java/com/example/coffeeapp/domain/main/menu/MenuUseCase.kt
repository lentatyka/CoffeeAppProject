package com.example.coffeeapp.domain.main.menu

import com.example.coffeeapp.common.Constants
import com.example.coffeeapp.common.State
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.data.main.order.model.OrderItemDto
import com.example.coffeeapp.domain.main.menu.local.LocalMenuRepository
import com.example.coffeeapp.domain.main.menu.remote.RemoteMenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MenuUseCase @Inject constructor(
    private val localMenuRepository: LocalMenuRepository,
    private val remoteRepository: RemoteMenuRepository,
    private val ownerId: Long
) {

    fun loadMenu(): Flow<State<Nothing>> {
        return flow {
            runCatching {
                remoteRepository.loadMenu(ownerId)
            }.onSuccess {
                emit(State.Success)
            }.onFailure { error ->
                emit(State.Error(error.localizedMessage))
            }
        }.onStart { emit(State.Loading) }
    }

    fun getMenu(): Flow<List<MenuItem>> {
        return localMenuRepository.getOrders(ownerId).map { orders ->
            remoteRepository.getMenu().map { menuItemDto ->
                MenuItem(
                    id = menuItemDto.id,
                    name = menuItemDto.name,
                    price = menuItemDto.price,
                    imageUrl = menuItemDto.imageUrl,
                    amount = orders.find { it.id == menuItemDto.id }?.amount ?: 0
                )
            }
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
                    amount = menuItem.amount + 1,
                    ownerId = this.ownerId
                )
            )
        }
    }

    suspend fun subtract(menuItem: MenuItem) {
        when (menuItem.amount) {
            0 -> {
                localMenuRepository.deleteOrder(menuItem.id, ownerId)
            }
            else -> {
                localMenuRepository.insertOrder(
                    OrderItemDto(
                        id = menuItem.id,
                        name = menuItem.name,
                        price = menuItem.price,
                        amount = menuItem.amount - 1,
                        ownerId = this.ownerId
                    )
                )
            }
        }
    }
}