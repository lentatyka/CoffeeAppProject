package com.example.coffeeapp.presentation.main.screens.menu

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.coffeeapp.common.State

@BindingAdapter("imageUrl", "loading", "error")
fun loadImage(view: ImageView, url: String, errorImage: Drawable, loadingImage: Drawable) {
    Glide.with(view)
        .load(url)
        .placeholder(loadingImage)
        .centerCrop()
        .error(errorImage)
        .into(view)
}

@BindingAdapter("errorMessage")
fun TextView.setError(state: State?) {
    when(state){
        null -> visibility = View.GONE
        else ->{
            when (state) {
                is State.Error -> {
                    text = state.message
                    visibility = View.VISIBLE
                }
                else -> visibility = View.GONE
            }
        }
    }
}