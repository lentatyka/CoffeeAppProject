package com.example.coffeeapp.di.main

import androidx.lifecycle.ViewModel
import com.example.coffeeapp.common.Constants
import com.example.coffeeapp.data.main.menu.local.LocalMenuRepositoryImpl
import com.example.coffeeapp.data.main.menu.remote.RemoteMenuRepositoryImpl
import com.example.coffeeapp.data.main.menu.remote.MenuServiceApi
import com.example.coffeeapp.di.ViewModelKey
import com.example.coffeeapp.domain.main.menu.local.LocalMenuRepository
import com.example.coffeeapp.domain.main.menu.remote.RemoteMenuRepository
import com.example.coffeeapp.presentation.main.screens.menu.MenuViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
abstract class MenuModule {

    @Binds
    @[IntoMap ViewModelKey(MenuViewModel::class)]
    abstract fun bindMenuViewModel(viewModel: MenuViewModel):ViewModel

    @Binds
    abstract fun bindMenuRepository(repository: RemoteMenuRepositoryImpl): RemoteMenuRepository

    @Binds
    abstract fun bindStorageMenuRepository(repo: LocalMenuRepositoryImpl): LocalMenuRepository

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