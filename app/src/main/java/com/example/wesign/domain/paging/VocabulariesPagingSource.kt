package com.example.wesign.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.repository.StudyRepository
import com.example.wesign.utils.DataPreferences
import com.example.wesign.utils.SharedPreferencesUtils
import com.example.wesign.utils.jsonToList
import com.example.wesign.utils.listToJson

class VocabulariesPagingSource(
    private val studyRepository: StudyRepository,
    private val topicId: Int ?= null
) : PagingSource<Int, Vocabulary>() {

    private var cachedVocabularies: List<Vocabulary>? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Vocabulary> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        if (cachedVocabularies == null) {
            val cachedVocabulariesJson = SharedPreferencesUtils.getString("DATA_VOCAL")
            if (cachedVocabulariesJson != null) {
                cachedVocabularies = jsonToList(cachedVocabulariesJson, Vocabulary::class.java)
            } else {
                val response = studyRepository.getAllVocabularies()
                if (response.code == 200) {
                    cachedVocabularies = response.data
                    val vocabulariesJson = listToJson(cachedVocabularies!!)
                    SharedPreferencesUtils.setString("DATA_VOCAL", vocabulariesJson)
                } else {
                    return LoadResult.Error(Exception(response.message))
                }
            }
        }

        if (topicId != null) {
            cachedVocabularies = cachedVocabularies?.filter { it.topicId == topicId }
        }

        val vocabularies =cachedVocabularies?: emptyList()
        val fromIndex = page * pageSize
        val toIndex = minOf(fromIndex + pageSize, vocabularies.size)
        val pagedData = if (fromIndex < vocabularies.size) vocabularies.subList(fromIndex, toIndex) else emptyList()

        return LoadResult.Page(
            data = pagedData,
            prevKey = if (page == 0) null else page - 1,
            nextKey = if (toIndex < vocabularies.size) page + 1 else null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Vocabulary>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

