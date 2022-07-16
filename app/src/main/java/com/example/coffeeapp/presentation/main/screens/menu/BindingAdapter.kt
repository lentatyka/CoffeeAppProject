package com.example.coffeeapp.presentation.main.screens.menu

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl", "loading", "error")
fun loadImage(view: ImageView, url: String, errorImage: Drawable, loadingImage: Drawable) {
    Glide.with(view)
        .load(url)
        .placeholder(loadingImage)
        .centerCrop()
        .error(errorImage)
        .into(view)
}