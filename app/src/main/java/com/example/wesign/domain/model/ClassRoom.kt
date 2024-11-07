package com.example.wesign.domain.model

import androidx.compose.ui.graphics.Color
import com.example.wesign.data.model.response.ClassResponse
import com.example.wesign.utils.generateRandomColor
import com.google.gson.annotations.SerializedName

data class ClassRoom(
    val classRoomId: Int,
    val content: String,
    val imageLocation: String? = null,
    val createdBy :String?,
    var color: Color = generateRandomColor()
)

fun ClassResponse.toDomainModel(): ClassRoom {
    return ClassRoom(
        classRoomId = this.classRoomId,
        content = this.content,
        imageLocation = this.imageLocation,
        createdBy = this.createdBy
    )
}