package com.example.coffeeapp.domain.main.shops.remote

import com.example.coffeeapp.data.main.shops.remote.ShopLocationDto
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ShopsLocationUseCase @Inject constructor(
    private val shopsRepository: ShopsRepository
) {
    @Throws(HttpException::class, IOException::class)
    suspend operator fun invoke(): ArrayList<ShopLocationDto> {
        return try {
            shopsRepository()
        } catch (e: HttpException) {
            throw e
        } catch (e: IOException) {
            throw e
        }
    }
}