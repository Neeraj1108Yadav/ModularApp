package com.nano.modularapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

/**
 * Created By Neeraj Yadav on 01/08/24
 */
class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    @Throws(NoConnectivityException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetConnectionAvailable()){
            throw NoConnectivityException("No Internet Connection")
           /*return Response.Builder()
               .request(chain.request())
               .protocol(chain.connection()?.protocol() ?: okhttp3.Protocol.HTTP_1_1)
               .code(503) // Service Unavailable
               .message("No internet connection")
               .body("No internet connection".toResponseBody(null))
               .build()*/
        }

        val request = chain.request()
        val response = chain.proceed(request)
        return response
    }

    private fun isInternetConnectionAvailable() : Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network as? Network) ?: return false
        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}