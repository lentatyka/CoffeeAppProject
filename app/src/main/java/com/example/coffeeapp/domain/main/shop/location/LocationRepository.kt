package com.example.coffeeapp.domain.main.shop.location

import android.location.Location
import androidx.lifecycle.LiveData


interface LocationRepository {

    fun updateLocation(location: Location)

    fun getLocation():LiveData<Location>

}