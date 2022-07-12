package com.example.coffeeapp.domain.main.menu

import com.example.coffeeapp.data.main.menu.ShopMenu

interface MenuRepository {
    suspend operator fun invoke(id: Long?):ArrayList<ShopMenu>
}