package com.example.wesign.domain.usecase.study.getallpart_use_case

import com.example.wesign.domain.model.Part
import com.example.wesign.domain.repository.StudyRepository
import com.example.wesign.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllPartUseCase @Inject constructor(private val studyRepository: StudyRepository) {
    suspend  operator fun invoke(lessonId: Int?) : Flow<Resource<List<Part>>> = flow {
        emit(Resource.Loading())
        try {
            val response = studyRepository.getAllParts(lessonId)
            if (response.code == 200) {
                emit(Resource.Success(response.data!!))
            } else {
                emit(Resource.Error(response.message))
            }
            } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }.flowOn(Dispatchers.Default)
}