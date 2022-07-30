package com.example.coffeeapp.data.main.order.local

import androidx.room.*
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.data.main.order.model.OrderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenuList(menuList: List<MenuItem>)

    @Query("UPDATE product SET amount = amount+1 WHERE id = :id AND amount < 9")
    suspend fun incrementItemAmount(id: Int)

    @Query("UPDATE product SET amount = amount-1 WHERE id = :id AND amount > 0")
    suspend fun decrementItemAmount(id: Int)

    @Query("DELETE FROM product")
    fun deleteOrder()

    @Query("SELECT * FROM product")
    fun getMenuList(): Flow<List<OrderItem>>

    @Query("SELECT * FROM product WHERE amount > 0")
    fun getOrder(): Flow<List<OrderItem>>

    @Query("SELECT SUM(price * amount) FROM product")
    fun getTotal(): Flow<Double>

    @Transaction
    suspend fun updateMenuList(menuList: List<MenuItem>) {
        deleteOrder()
        insertMenuList(menuList)
    }
}