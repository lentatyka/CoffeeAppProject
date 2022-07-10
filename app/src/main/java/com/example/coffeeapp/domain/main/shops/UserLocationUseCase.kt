package com.example.coffeeapp.domain.main.shops

import android.location.Location
import com.example.coffeeapp.data.main.shops.location.LocationDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserLocationUseCase @Inject constructor(
    private val location: LocationRepository
) {
    @Throws(SecurityException::class)
    suspend operator fun invoke(): Flow<Location> {
        return try {
            location()
        } catch (e: SecurityException) {
            throw e
        }
    }
}