package com.example.coffeeapp.data.main.shops

import com.google.gson.annotations.SerializedName

data class Point(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
