package com.example.wesign.domain.model

import com.example.wesign.data.model.response.UserDetailResponse
import com.google.gson.annotations.SerializedName

data class UserDetail(
    var id : Int?,
    var name: String,
    val email: String,
    var phoneNumber: String?,
    var address: String?,
    var age: String?,
    var gender: String?,
    val avatarLocation:String?
)

fun UserDetailResponse.toDomain(): UserDetail {
    return UserDetail(
        id = this.id,
        name = this.name,
        email = this.email,
        phoneNumber = this.phoneNumber,
        address = this.address,
        age = this.age,
        gender = this.gender,
        avatarLocation = this.avatarLocation
    )
}
