package com.example.coffeeapp.domain.main.shop

import android.location.Location
import com.example.coffeeapp.common.State
import com.example.coffeeapp.domain.main.shop.location.LocationRepository
import com.example.coffeeapp.domain.main.shop.model.Shop
import com.example.coffeeapp.domain.main.shop.remote.ShopRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
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
                emit(State.Success)
            }.onFailure {
                emit(State.Error(it.localizedMessage))
            }
        }
    }

    fun getShopListLocation(): Flow<List<Shop>> {
        return locationRepository.getLocation().map { location ->
            shopRepository.getShopListDto().map { shopDto ->
                Shop(
                    id = shopDto.id.toLong(),
                    name = shopDto.name,
                    point = shopDto.point,
                    distance = location.distanceTo(Location("").apply {
                        latitude = shopDto.point.latitude
                        longitude = shopDto.point.longitude
                    }).toInt()
                )
            }
        }.onStart {
            shopRepository.getShopListDto().map { shopDto ->
                Shop(
                    id = shopDto.id.toLong(),
                    name = shopDto.name,
                    point = shopDto.point
                )
            }.also {
                emit(it)
            }
        }
    }
}