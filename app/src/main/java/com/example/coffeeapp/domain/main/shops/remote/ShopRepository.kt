package com.example.coffeeapp.domain.main.shops.remote

import com.example.coffeeapp.data.main.shops.remote.ShopLocationDto

interface ShopRepository {
    suspend fun loadShopsLocationDtoList()
    fun getShopLocationDtoList(): ArrayList<ShopLocationDto>
}