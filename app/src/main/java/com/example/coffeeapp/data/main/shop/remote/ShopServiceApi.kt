package com.example.coffeeapp.data.main.shop.remote

import kotlinx.coroutines.delay
import retrofit2.http.GET
import javax.inject.Inject

interface ShopServiceApi {

    @GET("/locations")
    suspend operator fun invoke(): ArrayList<ShopDto>

    class FakeShopService @Inject constructor() : ShopServiceApi {
        private val shopsList = arrayListOf(
            ShopDto(1.0, "Горожанин", Point(56.833004, 60.603296)),
            ShopDto(2.0, "Eggs On", Point(56.833610, 60.604371)),
            ShopDto(3.0, "Ламаджо", Point(56.834831, 60.601059)),
            ShopDto(4.0, "Петров двор", Point(56.830837, 60.613060)),
            ShopDto(5.0, "Smart Cafe", Point(56.832398, 60.617192)),
            ShopDto(6.0, "Золотой бамбук", Point(56.835451, 60.610249))
        )

        override suspend fun invoke(): ArrayList<ShopDto> {
            delay(2000) // Loading from net imitation
            return shopsList
        }
    }
}
