package com.example.coffeeapp.data.main.shop.local

import android.location.Location
import com.example.coffeeapp.domain.main.shop.location.LocationRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor() : LocationRepository {

    private val location = MutableSharedFlow<Location>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun updateLocation(location: Location) {
        this.location.tryEmit(location)
    }

    override fun getLocation() = location

}