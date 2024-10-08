package com.nano.modularapp.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nano.modularapp.api.UserService
import com.nano.modularapp.constant.Constant
import com.nano.modularapp.model.User
import com.nano.modularapp.model.UserRequest
import com.nano.modularapp.model.UserResponse
import com.nano.modularapp.network.NetworkConnectionInterceptor
import com.nano.modularapp.network.NetworkResult
import com.nano.modularapp.network.NoConnectivityException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * Created By Neeraj Yadav on 22/07/24
 */
//https://medium.com/androiddevelopers/hilt-testing-best-practices-in-the-mad-skills-series-8186a57eee2c
class UserRepository @Inject constructor(private val userService: UserService) : OnBoardRepository {

    private val _networkResultState:MutableLiveData<NetworkResult<UserResponse>> = MutableLiveData()
    val networkResultState:LiveData<NetworkResult<UserResponse>>
        get() = _networkResultState

    override suspend fun login(userRequest: UserRequest) {
       try{
           _networkResultState.postValue(NetworkResult.Failure(""))
           _networkResultState.postValue(NetworkResult.Loading())
           val response = userService.login(userRequest)
           handleResponse(response)
       }catch (exception:IOException){
           _networkResultState.postValue(NetworkResult.Failure(exception.message))
       }catch (exception:Exception){
           _networkResultState.postValue(NetworkResult.Failure(exception.message))
       }
    }

    override suspend fun signup(userRequest: UserRequest) {
        _networkResultState.postValue(NetworkResult.Loading())
        val response = userService.signup(userRequest)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<UserResponse>) {
        try {
            Log.d(Constant.TAG,"Thread = ${Thread.currentThread()}")
            if (response.isSuccessful && response.body() != null) {
                _networkResultState.postValue(NetworkResult.Success(response.body()))
            } else if (response.errorBody() != null) {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _networkResultState.postValue(NetworkResult.Failure(errorObj.optString("message")))
            } else if(response.code() == 503){ // Internet Connectivity Failure Manual Code
                _networkResultState.postValue(NetworkResult.Failure(response.raw().message))
            }else{
                _networkResultState.postValue(NetworkResult.Failure("Something went wrong"))
            }
        }catch (exception:Exception){
            _networkResultState.postValue(NetworkResult.Failure(exception.message))
        }

    }

}