package com.nano.modularapp.di

import android.content.Context
import com.nano.modularapp.MockServer
import com.nano.modularapp.api.AppService
import com.nano.modularapp.api.UserService
import com.nano.modularapp.constant.Constant
import com.nano.modularapp.network.AuthInterceptor
import com.nano.modularapp.network.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created By  on 07/08/24
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class NetworkModuleTest : NetworkModule() {

    override fun baseUrl(): HttpUrl {
        return super.baseUrl()
    }

    /*@Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):UserService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .build().create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient():OkHttpClient{
        return OkHttpClient.Builder().build()
    }*/

}