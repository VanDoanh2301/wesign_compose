package com.example.wesign.presentation.ui.auth.register



sealed class RegisterScreenEvent {
    data class generateOtp(val name:String, val email:String, val password:String, val role:String) : RegisterScreenEvent()
}

data class RegisterScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val messageResult: String? = null,
    val isOtpVerified: Boolean = false,
    val email: String = "",
    val name: String = "",
    val password: String = "",
)