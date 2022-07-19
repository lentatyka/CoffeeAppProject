package com.example.coffeeapp.domain.main.shops.location

import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val location: LocationRepository
) {
    @Throws(SecurityException::class)
    suspend operator fun invoke() = location()
}