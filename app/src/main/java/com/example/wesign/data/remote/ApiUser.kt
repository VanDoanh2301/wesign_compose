package com.example.wesign.data.remote

import com.example.wesign.data.model.request.GenerateOtpRequest
import com.example.wesign.data.model.request.LoginRequest
import com.example.wesign.data.model.request.ValidateOtpRequest
import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.data.model.response.LoginResponse
import com.example.wesign.data.model.response.UserDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiUser {
    @POST("register/generate-otp")
    suspend fun generateOtp(@Body generateOtpRequest: GenerateOtpRequest): Response<HostResponse<Nothing>>

    @POST("register/validate-otp")
    suspend  fun validateOtp(@Body validateOtpRequest: ValidateOtpRequest): Response<HostResponse<Nothing>>

    @POST("auth/login")
    suspend  fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("users/me/v2")
    suspend fun getUserDetail(): Response<HostResponse<UserDetailResponse>>


}