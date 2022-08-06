package com.example.coffeeapp.di.login

import androidx.lifecycle.ViewModel
import com.example.coffeeapp.common.Constants.BASE_URL
import com.example.coffeeapp.data.login.network.LoginRepositoryImpl
import com.example.coffeeapp.data.login.network.LoginServiceApi
import com.example.coffeeapp.data.login.storage.SaveSharedPreferencesStorage
import com.example.coffeeapp.di.ViewModelKey
import com.example.coffeeapp.domain.login.network.LoginRepository
import com.example.coffeeapp.domain.login.storage.SaveStorage
import com.example.coffeeapp.presentation.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
interface LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    @LoginScope
    fun bindViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    fun bindLoginRepository(repository: LoginRepositoryImpl): LoginRepository

    @FakeLoginApi
    @Binds
    fun bindFakeLoginAPi(api: LoginServiceApi.FakeLoginService): LoginServiceApi

    @Binds
    fun bindSaveStorage(storage: SaveSharedPreferencesStorage):SaveStorage

    companion object{

        @RetrofitLoginApi
        @Provides
        @LoginScope
        fun provideLoginServiceApi(): LoginServiceApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginServiceApi::class.java)
        }
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RetrofitLoginApi

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class FakeLoginApi

