package com.example.coffeeapp.presentation.main.screens.shops

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.coffeeapp.R
import com.example.coffeeapp.data.main.shops.remote.Point

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