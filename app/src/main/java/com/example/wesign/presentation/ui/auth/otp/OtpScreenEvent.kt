package com.example.wesign.presentation.ui.auth.otp

sealed class OtpScreenEvent {
    data class validateOtp(val email:String, val otp:String) : OtpScreenEvent()
}

data class OtpScreenState(
    val isLoading: Boolean = false,
    val messageResult: String? = null,
    val isOtpVerified: Boolean = false )