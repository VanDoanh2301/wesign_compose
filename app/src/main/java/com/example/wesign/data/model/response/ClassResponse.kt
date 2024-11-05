package com.example.wesign.data.model.response

import com.google.gson.annotations.SerializedName

data class ClassResponse(
    @SerializedName("classRoomId")
    val classRoomId: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("imageLocation")
    val imageLocation: String,
    @SerializedName("createdBy")
    val createdBy :String,

)
