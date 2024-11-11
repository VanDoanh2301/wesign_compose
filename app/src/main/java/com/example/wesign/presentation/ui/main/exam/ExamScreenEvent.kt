package com.example.wesign.presentation.ui.main.exam

import com.example.wesign.domain.model.Exam

sealed class ExamScreenEvent {
    data object GetAllExam : ExamScreenEvent()
}

data class ExamScreenState(
    val error: String = "",
    val isLoading: Boolean = false,
    val listExams: List<Exam> = emptyList()
)