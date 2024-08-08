package com.nano.modularapp.repo

import com.nano.modularapp.model.UserRequest

/**
 * Created By Neeraj Yadav on 22/07/24
 */
interface OnBoardRepository {

    suspend fun login(userRequest: UserRequest)
    suspend fun signup(userRequest: UserRequest)
}