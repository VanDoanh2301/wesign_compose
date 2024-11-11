package com.example.wesign.data.model.response

import com.google.gson.annotations.SerializedName

data class ExamResponse(
    @SerializedName("classRoomId")
    val classRoomId: Int,
    @SerializedName("examId")
    val examId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("numberOfQuestions")
    val numberOfQuestions: Int,
    @SerializedName("private")
    val isPrivate: Boolean
)

data class ContentExamResponse(
    @SerializedName("content")
    val content:List<ExamResponse>
)