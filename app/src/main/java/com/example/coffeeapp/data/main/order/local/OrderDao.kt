package com.example.coffeeapp.data.main.order.local

import androidx.room.*
import com.example.coffeeapp.data.main.order.model.OrderItemDto
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Query("UPDATE orders SET amount =:amount WHERE id =:id AND ownerId =:ownerId")
    suspend fun updateOrderAmount(id: Int, amount: Int, ownerId: Long)

    @Query("DELETE FROM orders WHERE id = :id AND ownerId = :ownerId")
    suspend fun deleteOrderItem(id: Int, ownerId: Long)

    @Query("DELETE FROM orders WHERE ownerId = :ownerId")
    suspend fun deleteOrder(ownerId: Long)

    @Query("SELECT * FROM orders WHERE ownerId = :ownerId")
    fun getOrder(ownerId: Long): Flow<List<OrderItemDto>>

    @Query("SELECT SUM(price * amount) FROM orders WHERE ownerId =:ownerId")
    fun getTotal(ownerId: Long): Flow<Double>
}