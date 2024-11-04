package com.example.wesign.data.model.response

data class HostResponse <T> (
    val code: Int,
    val data: T? = null,
    val message: String
)