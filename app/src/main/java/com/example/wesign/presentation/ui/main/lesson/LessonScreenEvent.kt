package com.example.wesign.presentation.ui.main.lesson

import com.example.wesign.domain.model.Lesson
import com.example.wesign.domain.model.Part

sealed class LessonScreenEvent {
    data class GetLessonByClassId(val classId: Int) : LessonScreenEvent()
    data class GetPartByLessonId(val lessonId: Int) : LessonScreenEvent()
}

data class LessonScreenState(
    val isLoading: Boolean = false,
    val listLesson: List<LessonPartState> = emptyList(),
    val error: String = ""
)

data class LessonPartState(
    val lesson: Lesson,
    val part: List<Part>
)