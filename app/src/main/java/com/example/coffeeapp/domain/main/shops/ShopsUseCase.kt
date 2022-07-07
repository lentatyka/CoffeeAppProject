package com.example.coffeeapp.domain.main.shops

import android.location.Location
import android.util.Log
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.shops.remote.Point
import com.example.coffeeapp.data.main.shops.remote.ShopLocationDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.math.sqrt

class ShopsUseCase @Inject constructor(
    private val shopsLocationUseCase: ShopsLocationUseCase,
    private val userLocationUseCase: UserLocationUseCase
) {

    suspend fun zzz(): Flow<Resource<List<ShopLocation>>> {
        return flow {
            emit(Resource.Loading)
            userLocationUseCase.invoke().map { location ->
                emit(Resource.Success(emptyList()))
                val b = withContext(Dispatchers.IO) {
                    async { shopsLocationUseCase.testero() }
                }
                emit(Resource.Loading)
                b.await().map { shop ->
                    ShopLocation(
                        id = shop.id,
                        name = shop.name,
                        point = shop.point,
                        distance = (calculateDistance(shop.point, location))
                    )
                }

            }.collect {
                emit(Resource.Success(it))
            }
        }
    }

    suspend operator fun invoke(): Flow<Resource<ShopLocation>> {
        return flow {
            emit(Resource.Loading)
            try {
                combine(
                    shopsLocationUseCase.testero().asFlow(),
                    userLocationUseCase.invoke(),
                    ::merge
                ).collect {
                    emit(Resource.Success(it))
                }

            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            } catch (e: SecurityException) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }
    }

    private fun merge(shopList: List<ShopLocationDto>, location: Location): List<ShopLocation> {
        return shopList.map { shop ->
            ShopLocation(
                id = shop.id,
                name = shop.name,
                point = shop.point,
                distance = (calculateDistance(shop.point, location))
            )
        }
    }

    private fun merge(shop: ShopLocationDto, location: Location): ShopLocation {
        return ShopLocation(
            id = shop.id,
            name = shop.name,
            point = shop.point,
            distance = (calculateDistance(shop.point, location))
        )
    }

    private fun calculateDistance(point: Point, location: Location): Double {
        val latitude = point.latitude - location.latitude
        val longitude = point.longitude - location.longitude
        return sqrt(latitude * latitude + longitude * longitude)
    }
}