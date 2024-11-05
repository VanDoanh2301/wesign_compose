package com.example.wesign.domain.model

import com.example.wesign.data.model.response.ClassResponse
import com.google.gson.annotations.SerializedName

data class ClassRoom(
    val classRoomId: Int,
    val content: String,
    val imageLocation: String,
    val createdBy :String,
)

fun ClassResponse.toDomainModel(): ClassRoom {
    return ClassRoom(
        classRoomId = this.classRoomId,
        content = this.content,
        imageLocation = this.imageLocation,
        createdBy = this.createdBy
    )
}