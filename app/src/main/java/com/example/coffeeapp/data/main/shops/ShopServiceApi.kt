package com.example.coffeeapp.data.main.shops

import retrofit2.http.GET
import javax.inject.Inject

interface ShopServiceApi {

    @GET("/locations")
    suspend operator fun invoke(): ArrayList<ShopLocationDto>

    class FakeShopService @Inject constructor() : ShopServiceApi {
        private val shopsList = arrayListOf(
            ShopLocationDto(1.0, "Горожанин", Point(56.833004, 60.603296)),
            ShopLocationDto(2.0, "Eggs On", Point(56.833610, 60.604371)),
            ShopLocationDto(3.0, "Ламаджо", Point(56.834831, 60.601059)),
            ShopLocationDto(4.0, "Петров двор", Point(56.830837, 60.613060)),
            ShopLocationDto(5.0, "Smart Cafe", Point(56.832398, 60.617192)),
            ShopLocationDto(6.0, "Золотой бамбук", Point(56.835451, 60.610249))
        )

        override suspend fun invoke(): ArrayList<ShopLocationDto> {
            return shopsList
        }
    }
}
