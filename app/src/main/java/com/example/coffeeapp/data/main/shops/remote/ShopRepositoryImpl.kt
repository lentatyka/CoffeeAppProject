package com.example.coffeeapp.data.main.shops.remote

import com.example.coffeeapp.di.main.FakeShopServiceApi
import com.example.coffeeapp.domain.main.shops.remote.ShopsRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    @FakeShopServiceApi private val shopServiceApi: ShopServiceApi
): ShopsRepository {
    override suspend fun invoke() = shopServiceApi()
}