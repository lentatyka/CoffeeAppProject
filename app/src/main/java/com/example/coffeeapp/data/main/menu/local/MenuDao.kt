package com.example.coffeeapp.data.main.menu.local

import androidx.room.*
import com.example.coffeeapp.data.main.order.model.OrderItemDto
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Query("SELECT * FROM orders WHERE ownerId = :ownerId")
    fun getOrders(ownerId: Long): Flow<List<OrderItemDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderItem(orderItem: OrderItemDto)

    @Delete
    suspend fun deleteOrderItem(orderItem: OrderItemDto)

}