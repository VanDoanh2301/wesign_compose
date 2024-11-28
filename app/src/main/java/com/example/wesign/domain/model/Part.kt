package com.example.wesign.domain.model

import android.os.Parcelable
import com.example.wesign.data.model.response.PartImageResList
import com.example.wesign.data.model.response.PartResponse
import com.example.wesign.data.model.response.PartVideoResList
import kotlinx.parcelize.Parcelize

@Parcelize
data class Part(
    val partId: Int,
    val partName: String,
    val videos: List<PartVideoRes>,
    val images: List<PartImageRes>,
    val lessonId: Int?
) : Parcelable

@Parcelize
data class PartImageRes(
    val partImageId: Int,
    val imageLocation: String,
    val partId: Int
): Parcelable
@Parcelize
data class PartVideoRes(
    val partVideoId: Int,
    val videoLocation: String,
    val partId: Int
): Parcelable

fun PartResponse.toDomainPart(): Part {
    return Part(
        partId = partId,
        partName = partName,
        videos =  partVideoResList.map { it.toDomainPartVideoRes() },
        images =partImageResList.map { it.toDomainPartImageRes() },
        lessonId = lessonId
    )
}

fun PartVideoResList.toDomainPartVideoRes(): PartVideoRes {
    return PartVideoRes(
        partVideoId = partVideoId,
        videoLocation = videoLocation,
        partId = partId
    )
}

fun PartImageResList.toDomainPartImageRes(): PartImageRes {
    return PartImageRes(
        partImageId = partImageId,
        imageLocation = imageLocation,
        partId = partId
    )
}