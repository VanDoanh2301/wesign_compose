package com.example.wesign.domain.usecase.user.login_use_case

import android.util.Log
import com.example.wesign.data.model.request.LoginRequest
import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.domain.model.AccessToken
import com.example.wesign.domain.repository.UserRepository
import com.example.wesign.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(loginRequest: LoginRequest): Flow<Resource<HostResponse<AccessToken>>> =
        flow {
            emit(Resource.Loading())
            delay(2000)
            try {
                val response = userRepository.login(loginRequest)
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