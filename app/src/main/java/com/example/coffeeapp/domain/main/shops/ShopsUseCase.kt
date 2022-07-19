package com.example.coffeeapp.domain.main.shops

import android.location.Location
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.shops.remote.Point
import com.example.coffeeapp.domain.main.shops.location.LocationUseCase
import com.example.coffeeapp.domain.main.shops.model.ShopLocation
import com.example.coffeeapp.domain.main.shops.remote.ShopsLocationUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.math.sqrt

class ShopsUseCase @Inject constructor(
    private val shopsLocationUseCase: ShopsLocationUseCase,
    private val locationUseCase: LocationUseCase
) {
    /*
    Returned ShopList without distance if Location permission denied
     */

    fun getShopsList(): Flow<Resource<List<ShopLocation>>> {
        return flow {
                emit(Resource.Loading)
                runCatching {
                    shopsLocationUseCase().map { shop ->
                        ShopLocation(
                            id = shop.id.toInt(),
                            name = shop.name,
                            point = shop.point
                        )
                    }
                }.onSuccess {shopList ->
                    locationUseCase().map { location ->
                        shopList.map { shop ->
                            ShopLocation(
                                id = shop.id,
                                name = shop.name,
                                point = shop.point,
                                distance = if(location == null) 0.0 else (calculateDistance(shop.point, location))
                            )
                        }
                    }.collect {
                        emit(Resource.Success(it))
                    }
                }.onFailure { error ->
                    emit(Resource.Error(error.localizedMessage ?: "unknown error"))
                }
        }
    }

    private fun calculateDistance(point: Point, location: Location): Double {
        val latitude = point.latitude - location.latitude
        val longitude = point.longitude - location.longitude
        return sqrt(latitude * latitude + longitude * longitude)
    }
}