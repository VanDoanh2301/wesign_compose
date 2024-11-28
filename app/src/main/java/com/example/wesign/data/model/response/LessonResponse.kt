package com.example.wesign.data.model.response

import com.google.gson.annotations.SerializedName

data class LessonResponse(
    @SerializedName("lessonId")
    val lessonId: Int,
    @SerializedName("lessonName")
    val lessonName: String,
    @SerializedName("imageLocation")
    val imageLocation: String?,
    @SerializedName("videoLocation")
    val videoLocation: String?,
    @SerializedName("classRoomId")
    val classRoomId: Int,
    @SerializedName("classRoomContent")
    val classRoomContent: String
)
