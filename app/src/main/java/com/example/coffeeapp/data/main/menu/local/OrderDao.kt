package com.example.coffeeapp.data.main.menu.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coffeeapp.data.main.menu.model.MenuItem
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenuList(menuList: List<MenuItem>)

    @Query("UPDATE product SET amount = amount+1 WHERE id = :id AND amount < 9")
    fun incrementItemAmount(id: Int)

    @Query("UPDATE product SET amount = amount-1 WHERE id = :id AND amount > 0")
    fun decrementItemAmount(id: Int)

    @Query("SELECT * FROM product")
    fun getMenuList():Flow<MenuItem>
}