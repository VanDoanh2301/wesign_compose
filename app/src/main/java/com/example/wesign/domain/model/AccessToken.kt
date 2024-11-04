package com.example.wesign.domain.model

import com.example.wesign.data.model.response.LoginResponse

data class AccessToken(
    val accessToken: String,
    val refreshToken: String
)

fun LoginResponse.toDomain(): AccessToken {
    return AccessToken(
        accessToken = this.accessToken ?: "",
        refreshToken = this.refreshToken ?: ""
    )
}
