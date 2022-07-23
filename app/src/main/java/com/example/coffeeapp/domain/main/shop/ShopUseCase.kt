package com.example.coffeeapp.domain.main.shop

import android.location.Location
import com.example.coffeeapp.common.State
import com.example.coffeeapp.domain.main.shop.location.LocationRepository
import com.example.coffeeapp.domain.main.shop.model.Shop
import com.example.coffeeapp.domain.main.shop.remote.ShopLocationUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ShopUseCase @Inject constructor(
    private val shopsLocationUseCase: ShopLocationUseCase,
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
                emit(State.Error(it.localizedMessage))
            }
        }
    }

    fun getShopListLocation(): Flow<List<Shop>> {
        return locationRepository.getLocation().map { location ->
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
        }.onStart {
            shopsLocationUseCase.getShopListDto().map {shop->
                Shop(
                    id = shop.id.toLong(),
                    name = shop.name,
                    point = shop.point
                )
            }.also {
                emit(it)
            }
        }
    }
}