package com.example.wesign.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VocabularyResponse(
    @SerializedName("vocabularyId") var id: Int,
    @SerializedName("content") var content: String,
    @SerializedName("vocabularyImageResList") var vocabularyImageResList : ArrayList<VocabularyImageRes>,
    @SerializedName("vocabularyVideoResList") var vocabularyVideoResList : ArrayList<VocabularyVideoResList>,
    @SerializedName("topicId") var topicId: Int,
    @SerializedName("topicContent") var topicContent:String,
    @SerializedName("note") var note: String?= null,
    @SerializedName("vocabularyType") var vocabularyType: String = WordType.WORD.name,
) : Parcelable {
}
@Parcelize
data class VocabularyImageRes(
    @SerializedName("vocabularyImageId") val vocabularyImageId: Int,
    @SerializedName("imageLocation") var imageLocation: String?,
    @SerializedName("vocabularyId") val vocabularyId: Int,
    @SerializedName("vocabularyContent") val vocabularyContent: String,
    @SerializedName("primary") val primary: Boolean
) : Parcelable
@Parcelize
data class VocabularyVideoResList(
    @SerializedName("vocabularyVideoId") val vocabularyVideoId: Int,
    @SerializedName("videoLocation") val videoLocation: String?,
    @SerializedName("vocabularyId") val vocabularyId: Int,
    @SerializedName("vocabularyContent") val vocabularyContent: String,
    @SerializedName("primary") val primary: Boolean
) : Parcelable

enum class WordType {
    WORD, SENTENCE, PARAGRAPH
}