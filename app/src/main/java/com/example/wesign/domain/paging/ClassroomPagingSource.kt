package com.example.wesign.domain.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.repository.StudyRepository
import kotlinx.coroutines.delay

class ClassroomPagingSource(
    private val studyRepository: StudyRepository
) : PagingSource<Int, ClassRoom>() {

    private var cachedClassrooms: List<ClassRoom>? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ClassRoom> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        delay(2000L)
        if (cachedClassrooms == null) {

            val response = studyRepository.getAllClassrooms()
            if (response.code == 200) {
                cachedClassrooms = response.data
            } else {
                return LoadResult.Error(Exception(response.message))
            }
        }

        val classrooms = cachedClassrooms ?: emptyList()
        val fromIndex = page * pageSize
        val toIndex = minOf(fromIndex + pageSize, classrooms.size)
        val pagedData = if (fromIndex < classrooms.size) classrooms.subList(fromIndex, toIndex) else emptyList()

        return LoadResult.Page(
            data = pagedData,
            prevKey = if (page == 0) null else page - 1,
            nextKey = if (toIndex < classrooms.size) page + 1 else null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ClassRoom>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
