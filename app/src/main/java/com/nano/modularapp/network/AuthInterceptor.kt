package com.nano.modularapp.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created By Neeraj Yadav on 05/08/24
 */
class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request() // Get the Original request
        val newRequest = request.newBuilder()

        newRequest.addHeader("Authorization","Bearer ") // Request Modified with Auth Header
        return chain.proceed(newRequest.build())
    }

}