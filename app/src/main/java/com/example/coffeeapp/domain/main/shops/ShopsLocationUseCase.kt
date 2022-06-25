package com.example.coffeeapp.domain.main.shops

import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.shops.ShopLocationDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ShopsLocationUseCase @Inject constructor(
    private val shopsRepository: ShopsRepository
) {
    suspend operator fun invoke(): Flow<Resource<ArrayList<ShopLocationDto>>> {
        return flow {
            emit(Resource.Loading)
            try {
                val shopsLocation = shopsRepository()
                emit(Resource.Success(shopsLocation))
            } catch (e: HttpException) {
                //Обработать коды ошибок!
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }catch (e: IOException){
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }
    }
}