package com.example.coffeeapp.domain.main.shops.location

import android.location.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocationUseCase @Inject constructor(
    private val location: LocationRepository
) {
    @Throws(SecurityException::class)
    suspend operator fun invoke() = location()
}