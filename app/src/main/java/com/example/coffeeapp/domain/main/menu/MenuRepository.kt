package com.example.coffeeapp.domain.main.menu

interface MenuRepository {
    suspend operator fun invoke(id: Int)
}