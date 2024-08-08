package com.nano.modularapp.api

import com.nano.modularapp.model.UserRequest
import com.nano.modularapp.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created By Neeraj Yadav on 22/07/24
 */
interface UserService {

    @POST("/v1/login")
    suspend fun login(@Body userRequest: UserRequest) : Response<UserResponse>

    @POST("/v1/signup")
    suspend fun signup(@Body userRequest: UserRequest) : Response<UserResponse>
}