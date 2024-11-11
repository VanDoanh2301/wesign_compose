package com.example.wesign.domain.usecase.study.getallquestion_use_case

import com.example.wesign.domain.model.Question
import com.example.wesign.domain.repository.StudyRepository
import com.example.wesign.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllQuestionUseCase  @Inject constructor(private val studyRepository: StudyRepository) {
    suspend operator fun invoke(classRoomId: Int): Flow<Resource<List<Question>>> = flow {
        emit(Resource.Loading())
        try {
            val response = studyRepository.getAllQuestionByClassRoomId(classRoomId)
            if (response.code == 200) {
                emit(Resource.Success(response.data!!))
            } else {
                emit(Resource.Error(response.message))
            }
            } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }.flowOn(Dispatchers.IO)
}