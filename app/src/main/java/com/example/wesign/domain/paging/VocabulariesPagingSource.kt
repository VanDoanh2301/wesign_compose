package com.example.wesign.domain.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.repository.StudyRepository
import com.example.wesign.utils.DataPreferences
import com.example.wesign.utils.SharedPreferencesUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay

class VocabulariesPagingSource(
    private val studyRepository: StudyRepository,
    private val dataPreferences: DataPreferences
) : PagingSource<Int, Vocabulary>() {

    private var cachedVocabularies: List<Vocabulary>? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Vocabulary> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        if (cachedVocabularies == null) {
            val cachedVocabulariesJson = SharedPreferencesUtils.getString("DATA_VOCAL")
            if (cachedVocabulariesJson != null) {
                cachedVocabularies = jsonToVocabularyList(cachedVocabulariesJson)
            } else {
                val response = studyRepository.getAllVocabularies()
                if (response.code == 200) {
                    cachedVocabularies = response.data
                    val vocabulariesJson = vocabularyListToJson(cachedVocabularies!!)
                    SharedPreferencesUtils.setString("DATA_VOCAL", vocabulariesJson)
                } else {
                    return LoadResult.Error(Exception(response.message))
                }
            }
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

private fun vocabularyListToJson(vocabularyList: List<Vocabulary>): String {
    return Gson().toJson(vocabularyList)
}

private fun jsonToVocabularyList(json: String): List<Vocabulary> {
    val listType = object : TypeToken<List<Vocabulary>>() {}.type
    return Gson().fromJson(json, listType)
}