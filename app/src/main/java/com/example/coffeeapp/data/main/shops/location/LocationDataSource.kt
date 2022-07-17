package com.example.coffeeapp.data.main.shops.location

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.os.Looper
import com.example.coffeeapp.domain.main.shops.location.LocationRepository
import com.google.android.gms.location.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val LOCATION_REQUEST_INTERVAL = 60L //seconds
private const val LOCATION_REQUEST_FASTEST_INTERVAL = 10L //seconds

class LocationDataSource @Inject constructor(context: Application): LocationRepository {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val locationRequest = LocationRequest.create().apply {

        interval = TimeUnit.SECONDS.toMillis(LOCATION_REQUEST_INTERVAL)
        fastestInterval = TimeUnit.SECONDS.toMillis(LOCATION_REQUEST_FASTEST_INTERVAL)

        priority = Priority.PRIORITY_HIGH_ACCURACY
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    override suspend fun invoke() = callbackFlow<Location> {
        val locationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.onEach {
                    trySend(it)
                }
            }
        }
        fusedLocationClient.lastLocation.addOnSuccessListener {
            trySend(it)
            throw SecurityException("DS")
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
        awaitClose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
}