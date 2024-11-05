package com.example.wesign.data.model.response

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("userId")
    var id : Int?,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phoneNumber")
    var phoneNumber: String,
    @SerializedName("address")
    var address: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("birthDay")
    var age: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("avatarLocation")
    val avatarLocation:String
) {
}