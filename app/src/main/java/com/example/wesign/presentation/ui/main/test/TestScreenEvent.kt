package com.example.wesign.presentation.ui.main.test

import com.example.wesign.domain.model.Answer
import com.example.wesign.domain.model.Question

sealed class TestScreenEvent {
    data class GetQuestionByClassRoomId(val classRoomId: Int) : TestScreenEvent()
    data class CheckAnswerCorrect(val answer: String, val page: Int) : TestScreenEvent()
}

data class TestScreenState(
    val error: String = "",
    val isLoading: Boolean = false,
    val listTestQuestions: List<QuestionState> = emptyList(),
    val countCorrect: Int = 0,
    val totalQuestion: Int = 0,
)

data class QuestionState(
    val question: Question? = null,
    val answers: List<Answer> = emptyList(),
    val answerCorrect: String = "",
    val answerUser: String = "",
)

