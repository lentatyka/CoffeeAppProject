package com.example.coffeeapp.data.main.shops.remote

import com.google.gson.annotations.SerializedName

data class ShopLocationDto(
    @SerializedName("id")
    val id: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("point")
    val point: Point
)
