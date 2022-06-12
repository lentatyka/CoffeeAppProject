package com.example.coffeeapp.di.login

import androidx.lifecycle.ViewModel
import com.example.coffeeapp.common.Constants.BASE_URL
import com.example.coffeeapp.data.network.login.LoginRepositoryImpl
import com.example.coffeeapp.data.network.login.LoginServiceApi
import com.example.coffeeapp.di.ActivityScope
import com.example.coffeeapp.di.ViewModelKey
import com.example.coffeeapp.domain.network.login.LoginRepository
import com.example.coffeeapp.presentation.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
interface LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    fun bindLoginRepository(repository: LoginRepositoryImpl):LoginRepository

    companion object{

        @Provides
        @ActivityScope
        fun provideLoginServiceApi(): LoginServiceApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginServiceApi::class.java)
        }
    }
}