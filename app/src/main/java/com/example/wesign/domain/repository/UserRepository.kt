package com.example.wesign.domain.repository

import com.example.wesign.data.model.request.GenerateOtpRequest
import com.example.wesign.data.model.request.LoginRequest
import com.example.wesign.data.model.request.ValidateOtpRequest
import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.data.model.response.LoginResponse
import com.example.wesign.domain.model.AccessToken


interface UserRepository {
    suspend fun generateOtp(generateOtpRequest: GenerateOtpRequest): HostResponse<Nothing>
    suspend fun validateOtp(validateOtpRequest: ValidateOtpRequest): HostResponse<Nothing>
    suspend fun login(loginRequest: LoginRequest): HostResponse<AccessToken>
}