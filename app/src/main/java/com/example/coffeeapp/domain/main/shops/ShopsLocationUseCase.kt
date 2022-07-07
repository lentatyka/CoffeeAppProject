package com.example.coffeeapp.domain.main.shops

import com.example.coffeeapp.data.main.shops.remote.ShopLocationDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ShopsLocationUseCase @Inject constructor(
    private val shopsRepository: ShopsRepository
) {
    @Throws(HttpException::class, IOException::class)
    suspend operator fun invoke(): Flow<List<ShopLocationDto>> {
        return try {
            flow { emit(shopsRepository()) }
        } catch (e: HttpException) {
            throw e
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun testero(): ArrayList<ShopLocationDto> {
        delay(3000)
        return shopsRepository()
    }
}