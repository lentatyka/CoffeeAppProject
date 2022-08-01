package com.example.coffeeapp.domain.main.menu.model

import com.example.coffeeapp.common.Mapper
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.data.main.order.model.OrderItem

class MenuMapper constructor(
    private val ownerId: Long
):Mapper<MenuItem, OrderItem> {
    override fun map(value: MenuItem) = OrderItem(
        id = value.id,
        name = value.name,
        price = value.price,
        amount = value.amount,
        ownerId = this.ownerId
    )
}