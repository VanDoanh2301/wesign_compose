package com.example.wesign.domain.model

import com.example.wesign.data.model.response.LessonResponse

data class Lesson(
    val lessonId: Int,
    val lessonName: String,
    val imageLocation: String?,
    val videoLocation: String?,
    val classRoomId: Int,
)

fun LessonResponse.toDomainModel(): Lesson {
    return Lesson(
        lessonId = lessonId,
        lessonName = lessonName,
        imageLocation = imageLocation,
        videoLocation = videoLocation,
        classRoomId = classRoomId
    )
}
