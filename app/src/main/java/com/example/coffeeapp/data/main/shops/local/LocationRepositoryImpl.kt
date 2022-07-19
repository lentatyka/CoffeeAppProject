package com.example.coffeeapp.data.main.shops.local

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.coffeeapp.domain.main.shops.location.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor() : LocationRepository {

    private val location = MutableLiveData<Location>()

    override fun updateLocation(location: Location) {
        this.location.value = location
    }

    override fun getLocation() = location

}