package com.example.coffeeapp.common

sealed class State<out R> {
    object Success : State<Nothing>()
    class Error(val message: String?) : State<Nothing>()
    object Loading : State<Nothing>()
}