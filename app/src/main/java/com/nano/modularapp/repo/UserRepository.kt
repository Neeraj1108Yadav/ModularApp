package com.nano.modularapp.repo

import com.nano.modularapp.api.UserService
import com.nano.modularapp.model.UserRequest
import javax.inject.Inject

/**
 * Created By Neeraj Yadav on 22/07/24
 */
class UserRepository @Inject constructor(private val userService: UserService) : OnBoardRepository {

    override suspend fun login(userRequest: UserRequest) {
       userService.login(userRequest)
    }

    override suspend fun signup(userRequest: UserRequest) {
        userService.signup(userRequest)
    }
}