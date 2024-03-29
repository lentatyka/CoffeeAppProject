package com.example.coffeeapp.data.main.shop.remote

import com.google.gson.annotations.SerializedName

data class ShopDto(
    @SerializedName("id")
    val id: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("point")
    val point: Point
)
