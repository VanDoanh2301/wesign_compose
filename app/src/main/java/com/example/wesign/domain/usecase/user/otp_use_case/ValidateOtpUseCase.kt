package com.example.wesign.domain.usecase.user.otp_use_case

import com.example.wesign.data.model.request.ValidateOtpRequest
import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.domain.repository.UserRepository
import com.example.wesign.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ValidateOtpUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(validateOtpRequest: ValidateOtpRequest): Flow<Resource<HostResponse<Nothing>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = userRepository.validateOtp(validateOtpRequest)
                if (response.code == 200) {
                    emit(Resource.Success(response))
                } else {
                    emit(Resource.Error(response.message))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }
        }.flowOn(Dispatchers.Default)
}