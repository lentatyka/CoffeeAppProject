package com.example.coffeeapp.domain.main.shops.remote

import com.example.coffeeapp.data.main.shops.remote.ShopDto

interface ShopRepository {
    suspend fun loadShopsLocationDtoList()
    fun getShopLocationDtoList(): ArrayList<ShopDto>
}