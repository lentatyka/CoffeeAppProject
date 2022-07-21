package com.example.coffeeapp.data.main.shop.local

import android.annotation.SuppressLint
import android.app.Application
import android.os.Looper
import com.example.coffeeapp.domain.main.shop.location.LocationRepository
import com.google.android.gms.location.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val LOCATION_REQUEST_INTERVAL = 2L //seconds
private const val LOCATION_REQUEST_FASTEST_INTERVAL = 2L //seconds

class LocationSource @Inject constructor(
    context: Application,
    private val locationRepository: LocationRepository
) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val locationRequest = LocationRequest.create().apply {

        interval = TimeUnit.SECONDS.toMillis(LOCATION_REQUEST_INTERVAL)
        fastestInterval = TimeUnit.SECONDS.toMillis(LOCATION_REQUEST_FASTEST_INTERVAL)

        priority = Priority.PRIORITY_HIGH_ACCURACY
    }
    private var locationCallback: LocationCallback? = null

    @SuppressLint("MissingPermission")
    fun startUpdateLocation(){
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.onEach {
                    locationRepository.updateLocation(it)
                }
            }
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback!!,
            Looper.getMainLooper()
        )
    }

    fun stopUpdateLocation(){
        locationCallback?.let {
            fusedLocationClient.removeLocationUpdates(it)
        }
    }

}