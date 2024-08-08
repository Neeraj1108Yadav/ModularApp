package com.nano.modularapp.network

/**
 * Created By Neeraj Yadav on 23/07/24
 */
sealed class NetworkResult<T>(val data:T? = null, val message:String? = null) {

    class Loading<T> : NetworkResult<T>()
    class Success<T>(data:T? = null) : NetworkResult<T>(data = data)
    class Failure<T>(message: String? = null) : NetworkResult<T>(message = message)
}
