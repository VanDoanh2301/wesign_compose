package com.example.wesign.domain.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.Topic
import com.example.wesign.domain.repository.StudyRepository
import com.example.wesign.utils.DATA_CLASSROOM
import com.example.wesign.utils.DATA_TOPIC
import com.example.wesign.utils.SharedPreferencesUtils
import com.example.wesign.utils.jsonToList
import com.example.wesign.utils.listToJson
import kotlinx.coroutines.delay

class ClassroomPagingSource(
    private val studyRepository: StudyRepository
) : PagingSource<Int, ClassRoom>() {

    private var cachedClassrooms: List<ClassRoom>? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ClassRoom> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        if (cachedClassrooms == null) {
            val cachedVocabulariesJson = SharedPreferencesUtils.getString(DATA_CLASSROOM)
            if (cachedVocabulariesJson != null) {
                cachedClassrooms= jsonToList(cachedVocabulariesJson, ClassRoom::class.java)
            } else {
                val response = studyRepository.getAllClassrooms()
                if (response.code == 200) {
                    cachedClassrooms = response.data
                    val topicsJson = listToJson( cachedClassrooms!!)
                    SharedPreferencesUtils.setString(DATA_CLASSROOM, topicsJson)
                } else {
                    return LoadResult.Error(Exception(response.message))
                }
            }
        }

        val classRooms = cachedClassrooms?: emptyList()
        val fromIndex = page * pageSize
        val toIndex = minOf(fromIndex + pageSize,  classRooms.size)
        val pagedData = if (fromIndex <  classRooms.size)  classRooms.subList(fromIndex, toIndex) else emptyList()

        return LoadResult.Page(
            data = pagedData,
            prevKey = if (page == 0) null else page - 1,
            nextKey = if (toIndex <  classRooms.size) page + 1 else null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ClassRoom>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
