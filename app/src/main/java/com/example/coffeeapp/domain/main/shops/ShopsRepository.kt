package com.example.coffeeapp.domain.main.shops

import com.example.coffeeapp.data.main.shops.ShopLocationDto

interface ShopsRepository {
    suspend operator fun invoke():ArrayList<ShopLocationDto>
}