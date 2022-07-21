package com.example.coffeeapp.domain.main.shop.remote

import com.example.coffeeapp.data.main.shop.remote.ShopDto

interface ShopRepository {
    suspend fun loadShopListDto()
    fun getShopListDto(): ArrayList<ShopDto>
}