package com.example.wesign.domain.model

import com.example.wesign.data.model.response.VocabularyImageRes
import com.example.wesign.data.model.response.VocabularyResponse
import com.example.wesign.data.model.response.VocabularyVideoResList

data class Vocabulary(
    val id: Int,
    val content: String,
    val images: List<VocabularyImage>,
    val videos: List<VocabularyVideo>,
    val topicId: Int,
    val topicContent: String,
    val note: String?,
    val type: WordType
)

data class VocabularyImage(
    val location: String?,
    val content: String,
    val primary: Boolean
)

data class VocabularyVideo(
    val location: String?,
    val content: String,
    val primary: Boolean
)

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