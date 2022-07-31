package com.example.coffeeapp.data.main.order.local

import androidx.room.*
import com.example.coffeeapp.data.main.order.model.OrderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrderItem(orderItem: OrderItem)

    @Delete
    suspend fun deleteOrderItem(orderItem: OrderItem)

    @Query("DELETE FROM product WHERE id = :id AND ownerId = :ownerId")
    fun deleteOrderItem(id: Int, ownerId: Int)

    @Query("DELETE FROM product WHERE ownerId = :ownerId")
    suspend fun deleteOrder(ownerId: Int)

    @Query("SELECT * FROM product WHERE ownerId = :ownerId")
    fun getOrder(ownerId: Int):Flow<List<OrderItem>>

    @Query("SELECT SUM(price * amount) FROM product")
    fun getTotal():Flow<Double>
}