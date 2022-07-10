package com.example.coffeeapp.data.main.menu

import retrofit2.http.GET
import retrofit2.http.Path

interface MenuServiceApi {

    @GET("/location/{id}/menu")
    suspend fun getMenu(@Path("id") id: Int): ArrayList<ShopMenu>
}