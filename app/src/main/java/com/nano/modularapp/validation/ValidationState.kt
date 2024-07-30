package com.nano.modularapp.validation

/**
 * Created By Neeraj Yadav on 22/07/24
 */
sealed class ValidationState {
    data class EmailError(val error:Int):ValidationState()
    data class PasswordError(val error:Int):ValidationState()
    data object Success:ValidationState()
}