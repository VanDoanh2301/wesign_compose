package com.example.wesign.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wesign.domain.model.Topic
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.repository.StudyRepository
import com.example.wesign.utils.DATA_TOPIC
import com.example.wesign.utils.DATA_VOCAL
import com.example.wesign.utils.DataPreferences
import com.example.wesign.utils.SharedPreferencesUtils
import com.example.wesign.utils.jsonToList
import com.example.wesign.utils.listToJson

class TopicsPagingSource(
    private val studyRepository: StudyRepository,
    private val classRoomId: Int? = null,
    private val query: String? = null
) : PagingSource<Int, Topic>() {

    private var cachedTopics: List<Topic>? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Topic> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        if ( cachedTopics == null) {
            val cachedVocabulariesJson = SharedPreferencesUtils.getString(DATA_TOPIC)
            if (cachedVocabulariesJson != null) {
                cachedTopics= jsonToList(cachedVocabulariesJson, Topic::class.java)
            } else {
                val response = studyRepository.getAllTopic()
                if (response.code == 200) {
                    cachedTopics = response.data
                    val topicsJson = listToJson( cachedTopics!!)
                    SharedPreferencesUtils.setString(DATA_TOPIC, topicsJson)
                } else {
                    return LoadResult.Error(Exception(response.message))
                }
            }
        }
        if (classRoomId != null) {
            cachedTopics = cachedTopics?.filter { it.classRoomId == classRoomId }
        }

        var topics = cachedTopics?: emptyList()

        if (query != null) {
            topics = topics.filter { it.content.contains(query, ignoreCase = true) }
        }

        val fromIndex = page * pageSize
        val toIndex = minOf(fromIndex + pageSize, topics.size)
        val pagedData = if (fromIndex < topics.size) topics.subList(fromIndex, toIndex) else emptyList()

        return LoadResult.Page(
            data = pagedData,
            prevKey = if (page == 0) null else page - 1,
            nextKey = if (toIndex < topics.size) page + 1 else null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Topic>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}