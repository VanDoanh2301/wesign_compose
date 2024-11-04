package com.example.wesign.data.repository

import android.util.Log
import com.example.wesign.data.model.request.GenerateOtpRequest
import com.example.wesign.data.model.request.LoginRequest
import com.example.wesign.data.model.request.ValidateOtpRequest
import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.data.model.response.LoginResponse
import com.example.wesign.data.remote.ApiUser
import com.example.wesign.domain.model.AccessToken
import com.example.wesign.domain.model.toDomain
import com.example.wesign.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: ApiUser) : UserRepository {

    /**
     * Generate OTP using the provided [generateOtpRequest].
     * @param generateOtpRequest The request containing OTP generation details.
     */
    override suspend fun generateOtp(generateOtpRequest: GenerateOtpRequest): HostResponse<Nothing> {
        return try {
            val response = api.generateOtp(generateOtpRequest)
            if (response.isSuccessful) {
                response.body() ?: HostResponse(
                    code = response.code(),
                    message = "Empty response body"
                )
            } else {
                HostResponse(
                    code = response.code(),
                    message = response.message()
                )
            }
        } catch (e: Exception) {
            HostResponse(
                code = -1,
                message = e.localizedMessage ?: "Unknown error occurred"
            )
        }
    }

    /**
     * Validate OTP using the provided [validateOtpRequest].
     * @param validateOtpRequest The request containing OTP validation details.
     */
    override suspend fun validateOtp(validateOtpRequest: ValidateOtpRequest): HostResponse<Nothing> {
        return try {
            val response = api.validateOtp(validateOtpRequest)
            if (response.isSuccessful) {
                response.body() ?: HostResponse(
                    code = response.code(),
                    message = "Empty response body"
                )
            } else {
                HostResponse(
                    code = response.code(),
                    message = response.message()
                )
            }
        } catch (e: Exception) {
            HostResponse(
                code = -1,
                message = e.localizedMessage ?: "Unknown error occurred"
            )
        }
    }

    /**
     * Login using the provided [loginRequest].
     * @param loginRequest The request containing login details.
     */
    override suspend fun login(loginRequest: LoginRequest): HostResponse<AccessToken> {
        try {
            val response = api.login(loginRequest)
            if (response.isSuccessful && response.body() != null) {
                return HostResponse(
                    code = response.code(),
                    data = response.body()!!.toDomain(),
                    message = response.message()
                )
            } else {
                return HostResponse(
                    code = response.code(),
                    message = response.errorBody()?.string() ?: "Unknown error occurred"
                )
            }
        } catch (e: Exception) {
            return HostResponse(
                code = -1,
                message = e.localizedMessage ?: "Unknown error occurred"
            )
        }

    }


}