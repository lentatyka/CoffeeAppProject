package com.example.coffeeapp.di.main

import androidx.lifecycle.ViewModel
import com.example.coffeeapp.common.Constants
import com.example.coffeeapp.data.main.shops.local.LocationRepositoryImpl
import com.example.coffeeapp.data.main.shops.remote.ShopRepositoryImpl
import com.example.coffeeapp.data.main.shops.remote.ShopServiceApi
import com.example.coffeeapp.di.ViewModelKey
import com.example.coffeeapp.domain.main.shops.location.LocationRepository
import com.example.coffeeapp.domain.main.shops.remote.ShopRepository
import com.example.coffeeapp.presentation.main.screens.shops.ShopViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
abstract class ShopModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShopViewModel::class)
    abstract fun bindViewModel(viewModel: ShopViewModel): ViewModel

    @Binds
    @FakeShopServiceApi
    abstract fun bindFakeShopServiceApi(api: ShopServiceApi.FakeShopService): ShopServiceApi

    @Binds
    abstract fun bindShopRepository(repository: ShopRepositoryImpl): ShopRepository

    @Binds
    abstract fun provideLocationDataSource(repository: LocationRepositoryImpl): LocationRepository

    companion object {

        @RetrofitShoServiceApi
        @Provides
        fun provideShopServiceApi(): ShopServiceApi {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ShopServiceApi::class.java)
        }


    }

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RetrofitShoServiceApi

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class FakeShopServiceApi