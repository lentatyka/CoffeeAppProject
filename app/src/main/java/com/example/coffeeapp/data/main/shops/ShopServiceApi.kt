package com.example.coffeeapp.data.main.shops

import retrofit2.http.GET

interface ShopServiceApi {

    @GET("/locations")
    suspend operator fun invoke(): ArrayList<ShopLocationDto>
}