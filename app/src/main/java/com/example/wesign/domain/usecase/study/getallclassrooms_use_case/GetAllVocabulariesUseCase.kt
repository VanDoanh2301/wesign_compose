package com.example.wesign.domain.usecase.study.getallclassrooms_use_case

import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.repository.StudyRepository
import com.example.wesign.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllVocabulariesUseCase @Inject constructor(private val studyRepository: StudyRepository) {
    suspend operator fun invoke(vocabularyType: String? = null): Flow<Resource<HostResponse<List<Vocabulary>>>> = flow {
            emit(Resource.Loading())
            try {
                val response = studyRepository.getAllVocabularies(vocabularyType)
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