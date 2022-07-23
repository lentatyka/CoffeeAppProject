package com.example.coffeeapp.data.main.menu.local

import android.view.MenuItem
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenuList(menuList: List<MenuItem>)

    @Query("UPDATE `order` SET amount = amount+1 WHERE id = :id AND amount < 9")
    fun incrementItemAmount(id: Int)

    @Query("UPDATE `order` SET amount = amount-1 WHERE id = :id AND amount > 0")
    fun decrementItemAmount(id: Int)

    @Query("SELECT * FROM `order`")
    fun getOrderList():Flow<MenuItem>
}