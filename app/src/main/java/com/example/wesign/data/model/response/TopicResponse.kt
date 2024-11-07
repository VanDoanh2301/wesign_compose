package com.example.wesign.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopicResponse(
    @SerializedName("topicId") var id: Int,
    @SerializedName("content") var content: String,
    @SerializedName("imageLocation") var imageLocation: String ?= null,
    @SerializedName("videoLocation") var videoLocation: String ?= null,
    @SerializedName("classRoomId") var classRoomId: Int ?= null,
    @SerializedName("classRoomContent") var classRoomContent: String ?= null,
    @SerializedName("private") var private: Boolean ?= null,
    ) : Parcelable {
}