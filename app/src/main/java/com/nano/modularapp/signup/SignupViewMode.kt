package com.nano.modularapp.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private var _inputEmailError:MutableLiveData<String> = MutableLiveData()
    val inputEmailError:LiveData<String> = _inputEmailError

    private var _inputPasswordError:MutableLiveData<String> = MutableLiveData()
    val inputPasswordError:LiveData<String> = _inputPasswordError

    fun registerUser(email:String,password:String){
        if(validInputFields(email, password)){
            viewModelScope.launch {
                userRepository.signup(UserRequest(email,"android",password))
            }
        }
    }

    private fun validInputFields(email:String,password:String): Boolean {
        return if(email.isEmpty()){
            _inputEmailError.value = "Email field is empty"
            false
        }else if(!email.matches(Constant.EMAIL_REGEX.toRegex())){
            _inputEmailError.value = "Invalid email"
            false
        }else if(password.isEmpty()){
            _inputPasswordError.value = "Password field is empty"
            false
        }else if(password.length < 6){
            _inputPasswordError.value = "Minimum length of password should be 6"
            false
        }else if(password.length > 15){
            _inputPasswordError.value = "Maximum length of password should be 15"
            false
        }else{
            true
        }
    }
}