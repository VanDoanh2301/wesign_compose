package com.example.wesign.data.remote

import com.example.wesign.data.model.request.ExamRequest
import com.example.wesign.data.model.response.ClassResponse
import com.example.wesign.data.model.response.ContentExamResponse
import com.example.wesign.data.model.response.ExamResponse
import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.data.model.response.LessonResponse
import com.example.wesign.data.model.response.PartResponse
import com.example.wesign.data.model.response.QuestionResponse
import com.example.wesign.data.model.response.TopicResponse
import com.example.wesign.data.model.response.VocabularyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiStudy {
    @GET("vocabularies/all")
    suspend fun getAllVocabularies(@Query("vocabularyType") vocabularyType: String? = null): Response<HostResponse<List<VocabularyResponse>>>

    @GET("classrooms/all")
    suspend fun getAllClassrooms(): Response<HostResponse<List<ClassResponse>>>

    @GET("topics/all")
    suspend fun getAllTopic(): Response<HostResponse<List<TopicResponse>>>

    @POST("exams/all-exams")
    suspend fun getAllExam(@Body examRequest: ExamRequest): Response<HostResponse<ContentExamResponse>>

    @GET("questions/{classRoomId}")
    suspend fun getQuestionsByClassRoomId(@Path("classRoomId") classRoomId: Int): Response<HostResponse<List<QuestionResponse>>>

    @GET("lessons/all")
    suspend fun  getAllLessons(@Query("classRoomId") classRoomId: Int?= null): Response<HostResponse<List<LessonResponse>>>

    @GET("parts/all")
    suspend fun getAllParts(@Query("lessonId") lessonId: Int ?= null): Response<HostResponse<List<PartResponse>>>
}