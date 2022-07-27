package com.example.coffeeapp.common

sealed class State<out R> {
    data class Success<out T>(val data: T?): State<T>()
    class Error(val message: String?):State<Nothing>()
    object Loading:State<Nothing>()
}