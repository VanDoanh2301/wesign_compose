package com.example.wesign.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class QuestionResponse(
    @SerializedName("questionId") val questionId: Int,
    @SerializedName("content") val content: String,
    @SerializedName("explanation") val explanation: String?,
    @SerializedName("imageLocation") val imageLocation: String?,
    @SerializedName("videoLocation") val videoLocation: String?,
    @SerializedName("questionType") val questionType: String = "ONE_ANSWER",
    @SerializedName("fileType") val fileType: String = "EXISTED",
    @SerializedName("answerResList") val answers:ArrayList<AnswerResponse>
)


@Parcelize
data class AnswerResponse(
    @SerializedName("answerId") var answerId:Int,
    @SerializedName("content") val content: String,
    @SerializedName("imageLocation") val imageLocation: String?,
    @SerializedName("videoLocation") val videoLocation: String?,
    @SerializedName("correct") val correct: Boolean
) : Parcelable