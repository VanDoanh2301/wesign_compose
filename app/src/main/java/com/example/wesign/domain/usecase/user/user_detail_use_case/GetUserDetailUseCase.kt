package com.example.wesign.domain.usecase.user.user_detail_use_case

import android.util.Log
import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.domain.model.UserDetail
import com.example.wesign.domain.repository.UserRepository
import com.example.wesign.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(): Flow<Resource<HostResponse<UserDetail>>> = flow {
        emit(Resource.Loading())
        try {
            val response = userRepository.getUserDetail()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error occurred"))
        }
    }.flowOn(Dispatchers.IO)

}