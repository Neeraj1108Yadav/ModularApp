package com.nano.modularapp.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nano.modularapp.R
import com.nano.modularapp.constant.Constant
import com.nano.modularapp.model.UserRequest
import com.nano.modularapp.repo.UserRepository
import com.nano.modularapp.validation.ValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By Neeraj Yadav on 22/07/24
 */

@HiltViewModel
class SignupViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel(){

    private var _errorMsg:MutableLiveData<Int> = MutableLiveData()
    val errorMsg:LiveData<Int> = _errorMsg

    fun registerUser(email:String,password:String){
        if(validInputFields(email, password)){
            viewModelScope.launch {
                userRepository.signup(UserRequest(email,"android",password))
            }
        }
    }

    private fun validInputFields(email:String,password:String): Boolean {
        return if(email.isEmpty()){
            _errorMsg.value = R.string.email_field_empty
            false
        }else if(!email.matches(Constant.EMAIL_REGEX.toRegex())){
            _errorMsg.value = R.string.email_invalid
            false
        }else if(password.isEmpty()){
            _errorMsg.value = R.string.password_field_empty
            false
        }else if(password.length < 6){
            _errorMsg.value = R.string.password_minimum_length
            false
        }else if(password.length > 15){
            _errorMsg.value = R.string.password_maximum_length
            false
        }else{
            true
        }
    }
}