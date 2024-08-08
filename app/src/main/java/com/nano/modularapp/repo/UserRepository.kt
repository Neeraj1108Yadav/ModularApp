package com.nano.modularapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nano.modularapp.api.UserService
import com.nano.modularapp.model.User
import com.nano.modularapp.model.UserRequest
import com.nano.modularapp.model.UserResponse
import com.nano.modularapp.network.NetworkResult
import org.json.JSONObject
import retrofit2.Response
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
       _networkResultState.postValue(NetworkResult.Loading())
        val response = userService.login(userRequest)
        handleResponse(response)
    }

    override suspend fun signup(userRequest: UserRequest) {
        _networkResultState.postValue(NetworkResult.Loading())
        val response = userService.signup(userRequest)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _networkResultState.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
             val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _networkResultState.postValue(NetworkResult.Failure(errorObj.optString("message")))
        } else {
            _networkResultState.postValue(NetworkResult.Failure("Something went wrong"))
        }
    }

}