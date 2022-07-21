package com.example.coffeeapp.domain.main.shops

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.coffeeapp.common.State
import com.example.coffeeapp.domain.main.shops.location.LocationRepository
import com.example.coffeeapp.domain.main.shops.model.Shop
import com.example.coffeeapp.domain.main.shops.remote.ShopsLocationUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ShopUseCase @Inject constructor(
    private val shopsLocationUseCase: ShopsLocationUseCase,
    private val locationRepository: LocationRepository
) {

    fun loadShopList(): Flow<State> {
        return flow {
            emit(State.Loading)
            kotlin.runCatching {
                shopsLocationUseCase.loadShopListDto()
            }.onSuccess {
                emit(State.Success)
            }.onFailure {
                emit(State.Error(it.localizedMessage ?: "unknown error"))
            }
        }
    }

    fun getShopList() = shopsLocationUseCase.getShopListDto().map { shopDto ->
        Shop(
            id = shopDto.id.toLong(),
            name = shopDto.name,
            point = shopDto.point,
        )
    }

    fun getShopListLocation(): LiveData<List<Shop>> {
        return Transformations.map(locationRepository.getLocation()) { location ->
            shopsLocationUseCase.getShopListDto().map { shop ->
                Shop(
                    id = shop.id.toLong(),
                    name = shop.name,
                    point = shop.point,
                    distance = location.distanceTo(Location("").apply {
                        latitude = shop.point.latitude
                        longitude = shop.point.longitude
                    }).toInt()
                )
            }
        }
    }
}