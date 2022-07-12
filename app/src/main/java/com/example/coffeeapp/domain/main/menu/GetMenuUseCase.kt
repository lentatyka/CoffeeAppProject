package com.example.coffeeapp.domain.main.menu

import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.menu.ShopMenu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMenuUseCase @Inject constructor(
    private val menuRepository: MenuRepository
) {
    suspend operator fun invoke(id: Long?): Flow<Resource<ArrayList<ShopMenu>>> {
        return flow {
            emit(Resource.Loading)
            try {
                emit(Resource.Success(menuRepository(id)))
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }
    }
}