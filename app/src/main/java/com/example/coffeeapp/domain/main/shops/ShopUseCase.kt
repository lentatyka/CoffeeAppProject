package com.example.coffeeapp.domain.main.shops

import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.shops.remote.Point
import com.example.coffeeapp.domain.main.shops.location.LocationRepository
import com.example.coffeeapp.domain.main.shops.model.Shop
import com.example.coffeeapp.domain.main.shops.remote.ShopsLocationUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.math.sqrt

class ShopUseCase @Inject constructor(
    private val shopsLocationUseCase: ShopsLocationUseCase,
    private val locationRepository: LocationRepository
) {

    fun loadShopList(): Flow<Resource<List<Shop>>> {
        return flow {
            emit(Resource.Loading)
            kotlin.runCatching {
                shopsLocationUseCase.loadShopsLocationDtoList()
            }.onSuccess {
                emit(Resource.Success(emptyList<Shop>()))
            }.onFailure {
                emit(Resource.Error(it.localizedMessage ?: "unknown error"))
            }
        }
    }

    fun getShopList() = shopsLocationUseCase.getShopLocationDtoList().map { shopDto ->
        Shop(
            id = shopDto.id.toLong(),
            name = shopDto.name,
            point = shopDto.point,
        )
    }

    fun getShopListLocation(): LiveData<List<Shop>> {
        return Transformations.map(locationRepository.getLocation()) { location ->
            shopsLocationUseCase.getShopLocationDtoList().map { shop ->
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

    private fun calculateDistance(point: Point, location: Location): Double {
        val latitude = point.latitude - location.latitude
        val longitude = point.longitude - location.longitude
        val z = sqrt(latitude * latitude + longitude * longitude)
        Log.d("TAG", "DISTA: $z")
        return sqrt(latitude * latitude + longitude * longitude)
    }
}