package com.example.coffeeapp.domain.main.shops.remote

import com.example.coffeeapp.data.main.shops.remote.ShopLocationDto

interface ShopsRepository {
    suspend operator fun invoke():ArrayList<ShopLocationDto>
}