package com.example.coffeeapp.data.main.order.local

import androidx.room.*
import com.example.coffeeapp.data.main.order.model.OrderItemDto
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrderItem(orderItemDto: OrderItemDto)

    @Delete
    suspend fun deleteOrderItem(orderItemDto: OrderItemDto)

    @Query("DELETE FROM orders WHERE id = :id AND ownerId = :ownerId")
    fun deleteOrderItem(id: Int, ownerId: Long)

    @Query("DELETE FROM orders WHERE ownerId = :ownerId")
    suspend fun deleteOrder(ownerId: Int)

    @Query("SELECT * FROM orders WHERE ownerId = :ownerId")
    fun getOrder(ownerId: Int):Flow<List<OrderItemDto>>

    @Query("SELECT SUM(price * amount) FROM orders WHERE ownerId =:ownerId")
    fun getTotal(ownerId: Long):Flow<Double>
}