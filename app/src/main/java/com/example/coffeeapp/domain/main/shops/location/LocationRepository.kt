package com.example.coffeeapp.domain.main.shops.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend operator fun invoke(): Flow<Location?>
}