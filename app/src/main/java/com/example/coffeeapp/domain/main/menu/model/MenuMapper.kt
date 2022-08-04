package com.example.coffeeapp.domain.main.menu.model

import com.example.coffeeapp.common.Mapper
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.data.main.order.model.OrderItemDto

class MenuMapper constructor(
    private val ownerId: Long
):Mapper<MenuItem, OrderItemDto> {
    override fun map(value: MenuItem) = OrderItemDto(
        id = value.id,
        name = value.name,
        price = value.price,
        amount = value.amount,
        ownerId = this.ownerId
    )
}