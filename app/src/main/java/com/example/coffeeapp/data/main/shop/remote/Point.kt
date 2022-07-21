package com.example.coffeeapp.data.main.shop.remote

import com.google.gson.annotations.SerializedName

data class Point(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
