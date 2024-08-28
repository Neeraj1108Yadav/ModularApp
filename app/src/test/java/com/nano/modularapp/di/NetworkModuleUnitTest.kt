package com.nano.modularapp.di

import com.nano.modularapp.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class NetworkModuleUnitTest{

   @Singleton
   @Provides
   fun provideRetrofit(mockWebServer: MockWebServer) : Retrofit.Builder{
       return Retrofit.Builder()
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(mockWebServer.url("/"))
   }

   @Singleton
   @Provides
   fun provideUserService(retrofit:Retrofit.Builder) : UserService{
       return retrofit.build().create(UserService::class.java)
   }

   @Singleton
   @Provides
   fun provideMockWebServer() : MockWebServer{
       return MockWebServer()
   }
}