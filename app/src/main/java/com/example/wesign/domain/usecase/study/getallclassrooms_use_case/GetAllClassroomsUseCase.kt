package com.example.wesign.domain.usecase.study.getallclassrooms_use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.paging.ClassroomPagingSource
import com.example.wesign.domain.repository.StudyRepository
import com.example.wesign.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetAllClassroomsUseCase @Inject constructor(
    private val studyRepository: StudyRepository
) {
   suspend operator fun invoke(query: String? = null): Flow<PagingData<ClassRoom>> {
        return Pager(
            config = PagingConfig(pageSize = 3, enablePlaceholders = false),
            pagingSourceFactory = { ClassroomPagingSource(studyRepository, query) }
        ).flow.flowOn(Dispatchers.Default)
    }
}


