package com.example.wesign.presentation.ui.auth.otp

sealed class OtpScreenEvent {
    data class validateOtp(val email:String, val otp:String) : OtpScreenEvent()
    data object resendOtp : OtpScreenEvent()
}

data class OtpScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isOtpVerified: Boolean = false,
    val isResend: Boolean = false,
    val time: String = "",
)