package com.example.wesign.domain.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.example.wesign.data.model.response.TopicResponse
import com.example.wesign.utils.generateRandomColor
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class Topic(
    var id: Int,
    var content: String,
    var imageLocation: String? = null,
    var videoLocation: String? = null,
    var classRoomId: Int? = null,
    var classRoomContent: String? = null,
    var private: Boolean? = null,
    var color: Color = generateRandomColor()
)


fun TopicResponse.toDomainModel(): Topic {
    return Topic(
        id = this.id,
        content = this.content,
        imageLocation = this.imageLocation,
        videoLocation = this.videoLocation,
        classRoomId = this.classRoomId,
        classRoomContent = this.classRoomContent,
        private = this.private,
    )
}