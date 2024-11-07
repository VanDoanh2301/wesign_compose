package com.example.wesign.domain.model


import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.example.wesign.data.model.response.VocabularyImageRes
import com.example.wesign.data.model.response.VocabularyResponse
import com.example.wesign.data.model.response.VocabularyVideoResList
import com.example.wesign.utils.generateRandomColor
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
data class Vocabulary(
    val id: Int,
    val content: String,
    val images: List<VocabularyImage> ?= null,
    val videos: List<VocabularyVideo> ?= null,
    val topicId: Int,
    val topicContent: String,
    val note: String?,
    val type: WordType,

) : Parcelable
@Parcelize

data class VocabularyImage(
    val location: String?,
    val content: String,
    val primary: Boolean
): Parcelable
@Parcelize

data class VocabularyVideo(
    val location: String?,
    val content: String,
    val primary: Boolean
): Parcelable

enum class WordType {
    WORD, SENTENCE, PARAGRAPH
}


fun VocabularyResponse.toDomainModel(): Vocabulary {
    return Vocabulary(
        id = this.id,
        content = this.content,
        images = this.vocabularyImageResList.map { it.toDomainModel() },
        videos = this.vocabularyVideoResList.map { it.toDomainModel() },
        topicId = this.topicId,
        topicContent = this.topicContent,
        note = this.note,
        type = WordType.valueOf(this.vocabularyType)
    )
}

fun VocabularyImageRes.toDomainModel(): VocabularyImage {
    return VocabularyImage(
        location = this.imageLocation,
        content = this.vocabularyContent,
        primary = this.primary
    )
}

fun VocabularyVideoResList.toDomainModel(): VocabularyVideo {
    return VocabularyVideo(
        location = this.videoLocation,
        content = this.vocabularyContent,
        primary = this.primary
    )
}