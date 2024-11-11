package com.example.wesign.data.model.request

import com.example.wesign.data.model.response.ExamResponse
import com.google.gson.annotations.SerializedName



data class ExamRequest(
    @SerializedName("classRoomId")
    val classRoomId: Int,
    @SerializedName("isPrivate")
    val isPrivate: String,
    @SerializedName("nameSearch")
    val nameSearch:String,
    @SerializedName("page")
    val page:Int,
    @SerializedName("size")
    val size:Int,
    @SerializedName("ascending")
    val ascending:String,
    @SerializedName("orderBy")
    val orderBy:String
)
