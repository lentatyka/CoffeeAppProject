package com.example.coffeeapp.data.main.shops.remote

import com.example.coffeeapp.di.main.FakeShopServiceApi
import com.example.coffeeapp.domain.main.shops.remote.ShopRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    @FakeShopServiceApi private val shopServiceApi: ShopServiceApi
): ShopRepository {

    private val shopListDto = ArrayList<ShopLocationDto>()
    override suspend fun loadShopsLocationDtoList() {
        shopListDto.clear()
        shopListDto.addAll(shopServiceApi())
    }

    override fun getShopLocationDtoList() = shopListDto

}