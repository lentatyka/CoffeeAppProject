package com.example.coffeeapp.domain.main.menu.remote

import com.example.coffeeapp.data.main.menu.model.MenuItem
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMenuUseCase @Inject constructor(
    private val menuRepository: MenuRepository
) {
    @Throws(IOException::class, HttpException::class)
    suspend operator fun invoke(id: Long?):ArrayList<MenuItem>{
        return menuRepository(id)
    }
}