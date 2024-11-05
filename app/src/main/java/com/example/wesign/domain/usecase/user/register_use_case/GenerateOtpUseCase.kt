package com.example.wesign.domain.usecase.user.register_use_case

import android.util.Log
import com.example.wesign.data.model.request.GenerateOtpRequest
import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.domain.repository.UserRepository
import com.example.wesign.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenerateOtpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(generateOtpRequest: GenerateOtpRequest): Flow<Resource<HostResponse<Nothing>>> = flow {
        emit(Resource.Loading())
        try {
            val response = userRepository.generateOtp(generateOtpRequest)
            if (response.code == 200) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
    }.flowOn(Dispatchers.Default)
}
