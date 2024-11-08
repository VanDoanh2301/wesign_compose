package com.example.wesign.domain.usecase.study.getalltopic_use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wesign.domain.model.Topic
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.paging.TopicsPagingSource
import com.example.wesign.domain.paging.VocabulariesPagingSource
import com.example.wesign.domain.repository.StudyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllTopicUseCase @Inject constructor(private val studyRepository: StudyRepository) {
    suspend operator fun invoke(classRoomId: Int ?= null, query: String? = null): Flow<PagingData<Topic>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { TopicsPagingSource(studyRepository, classRoomId, query) }
        ).flow.flowOn(Dispatchers.Default)
    }

}