package com.example.coffeeapp.common

sealed class State {
    object Success: State()
    class Error(val message: String?):State()
    object Loading:State()
}