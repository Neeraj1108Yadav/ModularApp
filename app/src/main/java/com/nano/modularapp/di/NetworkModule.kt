package com.nano.modularapp.di

import android.content.Context
import com.nano.modularapp.api.AppService
import com.nano.modularapp.api.UserService
import com.nano.modularapp.constant.Constant
import com.nano.modularapp.network.AuthInterceptor
import com.nano.modularapp.network.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created By Neeraj Yadav on 22/07/24
 */

@InstallIn(SingletonComponent::class)
@Module
open class NetworkModule {

    protected open fun baseUrl() = Constant.MOCK_URL.toHttpUrl()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl())
    }

    @Singleton
    @Provides
    fun provideUserService(retrofitBuilder: Retrofit.Builder,
                           okHttpClient: OkHttpClient.Builder,): UserService {
        return retrofitBuilder.client(okHttpClient.build())
             .build()
             .create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppService(retrofitBuilder: Retrofit.Builder,
                         okHttpClient: OkHttpClient.Builder,
                         authInterceptor: AuthInterceptor): AppService {
        okHttpClient.addInterceptor(authInterceptor)
        return retrofitBuilder.client(okHttpClient.build())
            .build()
            .create(AppService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpBuilder(
        networkConnectionInterceptor: NetworkConnectionInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .addInterceptor(loggingInterceptor)
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideNetworkInterceptor(@ApplicationContext context: Context): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }

    @Singleton
    @Provides
    fun provideAuthorizationInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }
}