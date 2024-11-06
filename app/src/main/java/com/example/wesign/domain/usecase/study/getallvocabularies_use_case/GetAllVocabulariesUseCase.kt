package com.example.wesign.domain.usecase.study.getallvocabularies_use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.paging.ClassroomPagingSource
import com.example.wesign.domain.paging.VocabulariesPagingSource
import com.example.wesign.domain.repository.StudyRepository
import com.example.wesign.utils.DataPreferences
import com.example.wesign.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllVocabulariesUseCase @Inject constructor(private val studyRepository: StudyRepository, private val dataPreferences: DataPreferences) {
    suspend operator fun invoke(): Flow<PagingData<Vocabulary>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { VocabulariesPagingSource(studyRepository, dataPreferences) }
        ).flow.flowOn(Dispatchers.Default)
    }
}