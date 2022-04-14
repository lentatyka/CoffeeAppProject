package com.example.coffeeapp.presentation.login

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun errorMessage(view: TextInputLayout, string: String?){
    view.error = string
}