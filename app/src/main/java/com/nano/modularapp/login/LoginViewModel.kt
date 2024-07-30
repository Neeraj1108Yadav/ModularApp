package com.nano.modularapp.login

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

//https://jossypaul.medium.com/writing-unit-tests-for-viewmodel-7dd55793d04c
@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){

    private var _inputFieldError: MutableLiveData<ValidationState> = MutableLiveData()
    val inputFieldError: LiveData<ValidationState> = _inputFieldError

    fun loginUser(email:String,password:String){
        if(validInputFields(email, password)){
            viewModelScope.launch {
                userRepository.login(UserRequest(email,"android",password))
            }
        }
    }

    private fun validInputFields(email:String,password:String): Boolean {
        return if(email.isEmpty()){
            _inputFieldError.value = ValidationState.EmailError(R.string.email_field_empty)
            false
        }else if(!email.matches(Constant.EMAIL_REGEX.toRegex())){
            _inputFieldError.value = ValidationState.EmailError(R.string.email_invalid)
            false
        }else if(password.isEmpty()){
            _inputFieldError.value = ValidationState.PasswordError(R.string.password_field_empty)
            false
        }else if(password.length < 6){
            _inputFieldError.value = ValidationState.PasswordError(R.string.password_minimum_length)
            false
        }else if(password.length > 15){
            _inputFieldError.value = ValidationState.PasswordError(R.string.password_maximum_length)
            false
        }else{
            true
        }
    }
}