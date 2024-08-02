package com.nano.modularapp.di

import com.nano.modularapp.api.UserService
import com.nano.modularapp.constant.Constant
import com.nano.modularapp.network.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created By Neeraj Yadav on 22/07/24
 */

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(Constant.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService{
        return retrofit.create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkConnectionInterceptor(networkConnectionInterceptor: NetworkConnectionInterceptor) : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .build()
    }
}