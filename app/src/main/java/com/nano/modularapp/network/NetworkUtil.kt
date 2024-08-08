package com.nano.modularapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created By Neeraj Yadav on 01/08/24
 */
class NetworkUtil @Inject constructor(@ApplicationContext private val context:Context) {

    fun isInternetConnectionAvailable() : Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network as? Network) ?: return false
        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}