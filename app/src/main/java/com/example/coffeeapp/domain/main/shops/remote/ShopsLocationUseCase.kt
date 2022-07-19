package com.example.coffeeapp.domain.main.shops.remote

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ShopsLocationUseCase @Inject constructor(
    private val shopRepository: ShopRepository
) {
    @Throws(HttpException::class, IOException::class)
    suspend fun loadShopsLocationDtoList() = shopRepository.loadShopsLocationDtoList()

    fun getShopLocationDtoList() = shopRepository.getShopLocationDtoList()
}