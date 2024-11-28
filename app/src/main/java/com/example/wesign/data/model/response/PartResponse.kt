package com.example.wesign.data.model.response

import com.google.gson.annotations.SerializedName

data class PartResponse(
    @SerializedName("partId")
    val partId: Int,
    @SerializedName("partName")
    val partName: String,
    @SerializedName("partImageResList")
    val partImageResList: List<PartImageResList>,
    @SerializedName("partVideoResList")
    val partVideoResList: List<PartVideoResList>,
    @SerializedName("lessonId")
    val lessonId: Int?
)


data class PartImageResList (
    @SerializedName("partImageId")
    val partImageId: Int,
    @SerializedName("imageLocation")
    val imageLocation: String,
    @SerializedName("partId")
    val partId: Int
)

data class PartVideoResList (
    @SerializedName("partVideoId")
    val partVideoId: Int,
    @SerializedName("videoLocation")
    val videoLocation: String,
    @SerializedName("partId")
    val partId: Int
)
