package com.example.wesign.data.remote

import com.example.wesign.data.model.response.ClassResponse
import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.data.model.response.TopicResponse
import com.example.wesign.data.model.response.VocabularyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiStudy {
    @GET("vocabularies/all")
    suspend fun getAllVocabularies(@Query("vocabularyType") vocabularyType: String? = null): Response<HostResponse<List<VocabularyResponse>>>

    @GET("classrooms/all")
    suspend fun getAllClassrooms(): Response<HostResponse<List<ClassResponse>>>

    @GET("topics/all")
    suspend fun getAllTopic(): Response<HostResponse<List<TopicResponse>>>

}