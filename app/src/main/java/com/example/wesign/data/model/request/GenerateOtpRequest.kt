package com.example.wesign.data.model.request

data class GenerateOtpRequest(
    val name:String?,
    val email:String?,
    val phone:String?,
    val password:String?
)
