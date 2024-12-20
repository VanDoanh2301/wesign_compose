package com.example.wesign.data.model.request

import com.google.gson.annotations.SerializedName

data class GenerateOtpRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("role")
    val role: String,
)
