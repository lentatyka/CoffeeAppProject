package com.example.coffeeapp.domain.main.menu.remote

import com.example.coffeeapp.data.main.menu.model.ShopMenu

interface MenuRepository {
    suspend operator fun invoke(id: Long?):ArrayList<ShopMenu>
}