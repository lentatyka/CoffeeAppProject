package com.example.coffeeapp.domain.main.order

import androidx.lifecycle.LiveData
import com.example.coffeeapp.data.main.menu.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    suspend fun add(id: Int)

    suspend fun sub(id: Int)

    fun getOrder(): Flow<List<MenuItem>>

    fun getTotal(): Flow<Double>

}