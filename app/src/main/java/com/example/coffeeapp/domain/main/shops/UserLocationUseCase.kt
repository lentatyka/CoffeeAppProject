package com.example.coffeeapp.domain.main.shops

import android.location.Location
import com.example.coffeeapp.data.main.shops.location.LocationDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocationUseCase @Inject constructor(
    private val location: LocationRepository
) {
    @Throws(SecurityException::class)
    suspend fun invoke(): Flow<Location> {
        return try {
            location()
        } catch (e: SecurityException) {
            throw e
        }
    }
}