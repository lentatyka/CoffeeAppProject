package com.example.coffeeapp.domain.main.menu.remote

import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.menu.model.ShopMenu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMenuUseCase @Inject constructor(
    private val menuRepository: MenuRepository
) {
    @Throws(IOException::class, HttpException::class)
    suspend operator fun invoke(id: Long?):ArrayList<ShopMenu>{
        return menuRepository(id)
    }
}