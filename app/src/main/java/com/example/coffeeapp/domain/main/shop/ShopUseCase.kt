package com.example.coffeeapp.domain.main.shop

import android.location.Location
import com.example.coffeeapp.common.State
import com.example.coffeeapp.domain.main.shop.location.LocationRepository
import com.example.coffeeapp.domain.main.shop.model.Shop
import com.example.coffeeapp.domain.main.shop.remote.ShopRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ShopUseCase @Inject constructor(
    private val shopRepository: ShopRepository,
    private val locationRepository: LocationRepository
) {

    fun loadShopList(): Flow<State<Nothing>> {
        return flow {
            kotlin.runCatching {
                shopRepository.loadShopListDto()
            }.onSuccess {
                emit(State.Success(null))
            }.onFailure {
                emit(State.Error(it.localizedMessage))
            }
        }
    }

    fun getShopListLocation(): Flow<List<Shop>> {
        return locationRepository.getLocation().map { location ->
            shopRepository.getShopListDto().map { shop ->
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
            shopRepository.getShopListDto().map {shop->
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