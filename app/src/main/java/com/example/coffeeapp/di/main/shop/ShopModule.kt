package com.example.coffeeapp.di.main.shop

import androidx.lifecycle.ViewModel
import com.example.coffeeapp.data.main.shop.local.LocationRepositoryImpl
import com.example.coffeeapp.data.main.shop.remote.ShopRepositoryImpl
import com.example.coffeeapp.data.main.shop.remote.ShopServiceApi
import com.example.coffeeapp.di.ViewModelKey
import com.example.coffeeapp.domain.main.shop.location.LocationRepository
import com.example.coffeeapp.domain.main.shop.remote.ShopRepository
import com.example.coffeeapp.presentation.main.screens.shop.ShopViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
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
    @ShopScope
    abstract fun bindShopRepository(repository: ShopRepositoryImpl): ShopRepository

    @Binds
    @ShopScope
    abstract fun bindLocationDataSource(repository: LocationRepositoryImpl): LocationRepository

    companion object {
        @Provides
        @RetrofitShoServiceApi
        fun provideShopServiceApi(retrofit: Retrofit): ShopServiceApi {
            return retrofit.create(ShopServiceApi::class.java)
        }
    }

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RetrofitShoServiceApi

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class FakeShopServiceApi