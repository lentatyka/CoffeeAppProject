package com.example.coffeeapp.di.main

import com.example.coffeeapp.common.Constants
import com.example.coffeeapp.data.main.menu.local.MenuRepa
import com.example.coffeeapp.data.main.menu.local.StorageMenuRepositoryImpl
import com.example.coffeeapp.data.main.menu.remote.MenuRepositoryImpl
import com.example.coffeeapp.data.main.menu.remote.MenuServiceApi
import com.example.coffeeapp.domain.main.menu.local.StorageMenuRepository
import com.example.coffeeapp.domain.main.menu.remote.MenuRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
abstract class MenuModule {

    @Binds
    abstract fun bindMenuRepository(repository: MenuRepositoryImpl): MenuRepository

    @Binds
    abstract fun bindStorageMenuRepository(repo: MenuRepa): StorageMenuRepository

    @Binds
    @FakeMenuServiceApi
    abstract fun bindFakeMenuServiceApi(api: MenuServiceApi.FakeMenuService): MenuServiceApi

    companion object {

        @RetrofitMenuServiceApi
        @Provides
        fun provideShopServiceApi(): MenuServiceApi {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MenuServiceApi::class.java)
        }
    }

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RetrofitMenuServiceApi

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class FakeMenuServiceApi