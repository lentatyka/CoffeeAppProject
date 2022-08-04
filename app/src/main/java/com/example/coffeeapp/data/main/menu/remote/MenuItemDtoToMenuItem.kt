package com.example.coffeeapp.data.main.menu.remote

import com.example.coffeeapp.common.Mapper
import com.example.coffeeapp.data.main.menu.model.MenuItem
import javax.inject.Inject

class MenuItemDtoToMenuItem @Inject constructor(
    private val ownerId: Long
):Mapper<MenuItemDto, MenuItem> {
    override fun map(value: MenuItemDto) = MenuItem(
        id = value.id,
        name = value.name,
        imageUrl = value.imageUrl,
        price = value.price,
        amount = 0,
    )
}