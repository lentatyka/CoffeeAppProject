package com.example.coffeeapp.data.main.shop.local

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.coffeeapp.di.main.MainScope
import com.example.coffeeapp.domain.main.shop.location.LocationRepository
import javax.inject.Inject

@MainScope
class LocationRepositoryImpl @Inject constructor() : LocationRepository {

    private val location = MutableLiveData<Location>()

    override fun updateLocation(location: Location) {
        this.location.value = location
    }

    override fun getLocation() = location

}