package com.example.coffeeapp.data.main.shops.local

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import com.example.coffeeapp.domain.main.shops.location.LocationRepository
import com.google.android.gms.location.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class LocationDataSource @Inject constructor(context: Application): LocationRepository {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    override suspend fun invoke() = callbackFlow {
       fusedLocationClient.lastLocation.addOnSuccessListener {
           trySend(it)
       }.addOnFailureListener{
           trySend(null)
       }
        awaitClose {

        }
    }
}