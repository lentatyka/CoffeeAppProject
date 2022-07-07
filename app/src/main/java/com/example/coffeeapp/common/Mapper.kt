package com.example.coffeeapp.common

interface Mapper<T, V> {
    fun map(value: T): V
}