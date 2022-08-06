package com.example.coffeeapp.presentation.main.screens

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.coffeeapp.R
import com.example.coffeeapp.common.State
import com.example.coffeeapp.data.main.shop.remote.Point

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
fun TextView.setError(state: State<Any>?) {
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

@BindingAdapter("distance", "point")
fun setDistance(textView: TextView, distance: Int?, point: Point){
    textView.text = distance?.let {range->
        if(range >=1000){
            textView.resources.getQuantityString(R.plurals.distance_var, 0,distance/1000)
        }
        else
            textView.resources.getQuantityString(R.plurals.distance_var, 1,distance)

    } ?: textView.resources.getString(R.string.location, point.longitude, point.latitude)
}