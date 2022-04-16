package com.example.coffeeapp.common

sealed class Resource<out T>() {
    class Success<out T>(data: T): Resource<T>()
    class Error(val message: String):Resource<Nothing>()
    object Loading:Resource<Nothing>()
}