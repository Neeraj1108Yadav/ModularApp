package com.nano.modularapp.validation

/**
 * Created By Neeraj Yadav on 22/07/24
 */
sealed class ValidationState {
    data class EmailError(val error:String):ValidationState()
    data class PasswordError(val error:String):ValidationState()
    data object Success:ValidationState()
}