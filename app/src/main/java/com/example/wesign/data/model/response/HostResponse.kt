package com.example.wesign.data.model.response

data class HostResponse <out T> (
    val code: Int,
    val data: T,
    val message: String
)