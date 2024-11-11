package com.example.wesign.domain.model

import com.example.wesign.data.model.response.ExamResponse

data class Exam(
    val examId: Int,
    val name: String,
    val numberOfQuestions: Int,
    val isPrivate: Boolean,
    val classRoomId: Int
)

fun ExamResponse.toDomainExam(): Exam {
    return Exam(
        examId = examId,
        name = name,
        numberOfQuestions = numberOfQuestions,
        isPrivate = isPrivate,
        classRoomId = classRoomId
    )

}