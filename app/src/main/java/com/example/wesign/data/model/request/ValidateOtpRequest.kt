package com.example.wesign.data.model.request

import com.google.gson.annotations.SerializedName

data class ValidateOtpRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("otpNum")
    val otpNum:Int
)
