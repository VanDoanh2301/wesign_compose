package com.example.wesign.presentation.ui.auth.login

sealed class LoginScreenEvent {
    data class onLogin(val email:String, val password:String): LoginScreenEvent()
}

data class LoginScreenState(
    val isLoading:Boolean = false,
    val error:String? = null,
    val isSuccessful:Boolean = false,
)