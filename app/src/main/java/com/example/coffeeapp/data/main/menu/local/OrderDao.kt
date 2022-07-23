package com.example.coffeeapp.data.main.menu.local

import androidx.room.*
import com.example.coffeeapp.data.main.menu.model.MenuItem
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenuList(menuList: List<MenuItem>)

    @Query("UPDATE product SET amount = amount+1 WHERE id = :id AND amount < 12")
    suspend fun incrementItemAmount(id: Int)

    @Query("UPDATE product SET amount = amount-1 WHERE id = :id AND amount > 0")
    suspend fun decrementItemAmount(id: Int)

    @Query("DELETE FROM product")
    fun deleteOrder()

    @Query("SELECT * FROM product")
    fun getMenuList():Flow<List<MenuItem>>

    @Transaction
    suspend fun updateMenuList(menuList: List<MenuItem>){
        deleteOrder()
        insertMenuList(menuList)
    }
}