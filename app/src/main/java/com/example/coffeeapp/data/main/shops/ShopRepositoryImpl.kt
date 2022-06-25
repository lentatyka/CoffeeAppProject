package com.example.coffeeapp.data.main.shops

import com.example.coffeeapp.di.main.FakeShopServiceApi
import com.example.coffeeapp.domain.main.shops.ShopsRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    @FakeShopServiceApi private val shopServiceApi: ShopServiceApi
):ShopsRepository{
    override suspend fun invoke() = shopServiceApi()
}