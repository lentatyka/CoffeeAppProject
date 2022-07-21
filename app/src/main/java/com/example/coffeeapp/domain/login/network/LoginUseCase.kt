package com.example.coffeeapp.domain.login.network


import com.example.coffeeapp.common.State
import com.example.coffeeapp.domain.login.storage.SaveStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val storage: SaveStorage
) {
    fun signIn(email: String, password: String): Flow<State> {
        return flow {
            emit(State.Loading)
            try {
                val userInfo = loginRepository.signIn(User(email, password))
                //Success. Save token to storage
                storage(userInfo)
                emit(State.Success)
            } catch (e: HttpException) {
                //Обработать коды ошибок!
                emit(State.Error(e.localizedMessage))
            } catch (e: IOException) {
                emit(State.Error(e.localizedMessage))
            }
        }
    }

    fun signUp(email: String, password: String): Flow<State> {
        return flow {
            emit(State.Loading)
            try {
                val userInfo = loginRepository.signUp(User(email, password))
                emit(State.Success)
            } catch (e: HttpException) {
                //Обработать коды ошибок!
                emit(State.Error(e.localizedMessage))
            } catch (e: IOException) {
                emit(State.Error(e.localizedMessage))
            }
        }
    }
}