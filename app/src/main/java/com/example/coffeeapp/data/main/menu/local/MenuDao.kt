package com.example.coffeeapp.data.main.menu.local

import androidx.room.*
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.data.main.order.model.OrderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenu(menuList: List<MenuItem>)

    @Query("DELETE FROM menu")
    suspend fun deleteMenu()

    @Query("SELECT * FROM menu")
    fun getMenu(): Flow<List<MenuItem>>

    @Transaction
    suspend fun updateMenu(menuList: List<MenuItem>){
        deleteMenu()
        insertMenu(menuList)
    }
}