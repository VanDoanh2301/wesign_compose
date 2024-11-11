package com.example.wesign.domain.model

import com.example.wesign.data.model.response.AnswerResponse
import com.example.wesign.data.model.response.QuestionResponse

data class Question(
    val questionId: Int,
    val content: String,
    val explanation: String?,
    val imageLocation: String?,
    val videoLocation: String?,
    val questionType: String,
    val fileType: String,
    val answers: List<Answer>
)

data class Answer(
    val answerId: Int,
    val content: String,
    val imageLocation: String?,
    val videoLocation: String?,
    val correct: Boolean
)

fun QuestionResponse.toDomainQuestion(): Question {
    return Question(
        questionId = questionId,
        content = content,
        explanation = explanation,
        imageLocation = imageLocation,
        videoLocation = videoLocation,
        questionType = questionType,
        fileType = fileType,
        answers = answers.map { it.toAnswer() }
    )
}

fun AnswerResponse.toAnswer(): Answer {
    return Answer(
        answerId = answerId,
        content = content,
        imageLocation = imageLocation,
        videoLocation = videoLocation,
        correct = correct
    )
}
