package com.example.wesign.data.repository

import com.example.wesign.data.model.request.ExamRequest
import com.example.wesign.data.model.response.HostResponse
import com.example.wesign.data.remote.ApiStudy
import com.example.wesign.domain.mapper.toDomain
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.Exam
import com.example.wesign.domain.model.Question
import com.example.wesign.domain.model.Topic
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.model.toDomainExam
import com.example.wesign.domain.model.toDomainModel
import com.example.wesign.domain.model.toDomainQuestion
import com.example.wesign.domain.repository.StudyRepository
import javax.inject.Inject

class StudyRepositoryImpl @Inject constructor(private val apiStudy: ApiStudy) : StudyRepository {
    /**
     * Get all vocabularies from the API.
     */
    override suspend fun getAllVocabularies(vocabularyType: String?): HostResponse<List<Vocabulary>> {
        try {
            val response = apiStudy.getAllVocabularies(vocabularyType)
            if (response.isSuccessful && response.body() != null) {
                val responseBody = response.body()
                return responseBody!!.toDomain { list ->
                    list.map { it.toDomainModel() }  //Map from data layer to domain layer
                }
            } else {
                return HostResponse(
                    code = response.code(),
                    message = response.errorBody()?.string() ?: "Unknown error occurred"
                )
            }
        } catch (e: Exception) {
            return HostResponse(
                code = -1,
                message = e.localizedMessage ?: "Unknown error occurred"
            )
        }

    }

    /**
     * Get all classrooms from the API.
     */
    override suspend fun getAllClassrooms(): HostResponse<List<ClassRoom>> {
        try {
            val response = apiStudy.getAllClassrooms()
            if (response.isSuccessful && response.body() != null) {
                val responseBody = response.body()
                return responseBody!!.toDomain { list ->
                    list.map { it.toDomainModel() }
                }
            } else {
                return HostResponse(
                    code = response.code(),
                    message = response.errorBody()?.string() ?: "Unknown error occurred"
                )
            }
        } catch (e: Exception) {
            return HostResponse(
                code = -1,
                message = e.localizedMessage ?: "Unknown error occurred"
            )

        }

    }

    /**
     * Get all topics from the API.
     */
    override suspend fun getAllTopic(): HostResponse<List<Topic>> {
        try {
            val response = apiStudy.getAllTopic()
            if (response.isSuccessful && response.body() != null) {
                val responseBody = response.body()
                return responseBody!!.toDomain { list ->
                    list.map { it.toDomainModel() }
                }
            } else {
                return HostResponse(
                    code = response.code(),
                    message = response.errorBody()?.string() ?: "Unknown error occurred"
                )
            }
        } catch (e: Exception) {
            return HostResponse(
                code = -1,
                message = e.localizedMessage ?: "Unknown error occurred"
            )
        }
    }

    /**
     * Get all exam.
     * @param examRequest The request containing exam details.
     * @return The list of exams.
     */
    override suspend fun getAllExam(examRequest: ExamRequest): HostResponse<List<Exam>> {
        try {
            val response = apiStudy.getAllExam(examRequest)
            if (response.isSuccessful && response.body() != null) {
               val listExams = response.body()!!.data?.content?.map {
                   it.toDomainExam()

               }
                return HostResponse(
                    code = response.code(),
                    data = listExams,
                    message = response.message()
                )
            } else {
                return HostResponse(
                    code = response.code(),
                    message = response.errorBody()?.string() ?: "Unknown error occurred"
                )
            }
        } catch (e: Exception) {
            return HostResponse(
                code = -1,
                message = e.localizedMessage ?: "Unknown error occurred"
            )
        }
    }

    /**
     * Get All Question By Class Room Id.
     * @param classRoomId The class room id.
     * @return The list of questions.
     */
    override suspend fun getAllQuestionByClassRoomId(classRoomId: Int): HostResponse<List<Question>> {
        try {
            val response = apiStudy.getQuestionsByClassRoomId(classRoomId)
            if (response.isSuccessful && response.body() != null) {
                return response.body()!!.toDomain { list -> list.map { it.toDomainQuestion() } }
            } else {
                return HostResponse(
                    code = response.code(),
                    message = response.errorBody()?.string() ?: "Unknown error occurred"
                )

            }
        } catch (e: Exception) {
            return HostResponse(
                code = -1,
                message = e.localizedMessage ?: "Unknown error occurred"
            )

        }
    }

}