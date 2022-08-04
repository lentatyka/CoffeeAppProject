package com.example.coffeeapp.data.main.shop.remote

import com.example.coffeeapp.di.main.shop.FakeShopServiceApi
import com.example.coffeeapp.domain.main.shop.remote.ShopRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    @FakeShopServiceApi private val shopServiceApi: ShopServiceApi
): ShopRepository {

    private val shopListDto = ArrayList<ShopDto>()

    @Throws(HttpException::class, IOException::class)
    override suspend fun loadShopListDto() {
        shopListDto.clear()
        shopListDto.addAll(shopServiceApi())
    }

    override fun getShopListDto() = shopListDto

}