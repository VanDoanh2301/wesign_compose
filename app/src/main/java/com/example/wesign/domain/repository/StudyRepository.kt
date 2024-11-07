package com.example.wesign.domain.repository

import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.Topic
import com.example.wesign.domain.model.Vocabulary

interface StudyRepository {
    suspend fun getAllVocabularies(vocabularyType: String? = null): HostResponse<List<Vocabulary>>
    suspend fun getAllClassrooms(): HostResponse<List<ClassRoom>>
    suspend fun getAllTopic(): HostResponse<List<Topic>>

}