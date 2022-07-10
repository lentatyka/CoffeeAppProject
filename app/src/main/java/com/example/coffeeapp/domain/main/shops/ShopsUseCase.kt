package com.example.coffeeapp.domain.main.shops

import android.location.Location
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.shops.remote.Point
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

    /*
    Returned ShopList without distance if Location permission denied
     */
    @Suppress("BlockingMethodInNonBlockingContext")
    fun getShopsList(): Flow<Resource<List<ShopLocation>>> {
        return flow {
            try {
                emit(Resource.Loading)
                val shopList = withContext(Dispatchers.IO) {
                    async { shopsLocationUseCase() }
                }.await().map { shop ->
                    ShopLocation(
                        id = shop.id,
                        name = shop.name,
                        point = shop.point
                    )
                }
                emit(Resource.Success(shopList))
                userLocationUseCase().map { location ->
                    shopList.map { shop ->
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
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            } catch (e: SecurityException) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }
    }

    private fun calculateDistance(point: Point, location: Location): Double {
        val latitude = point.latitude - location.latitude
        val longitude = point.longitude - location.longitude
        return sqrt(latitude * latitude + longitude * longitude)
    }
}