package com.example.coffeeapp.domain.main.shops.location

import com.example.coffeeapp.data.main.shops.local.LocationSource
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val location: LocationSource
) {
    @Throws(SecurityException::class)
    fun startUpdateLocation() = location.startUpdateLocation()

    @Throws(SecurityException::class)
    fun stopUpdateLocation() = location.stopUpdateLocation()
}