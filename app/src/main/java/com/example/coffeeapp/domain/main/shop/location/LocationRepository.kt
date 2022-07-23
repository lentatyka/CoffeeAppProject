package com.example.coffeeapp.domain.main.shop.location

import android.location.Location
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow


interface LocationRepository {

    fun updateLocation(location: Location)

    fun getLocation(): Flow<Location>

}