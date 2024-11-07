package com.example.wesign.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import com.example.wesign.domain.model.Vocabulary
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

const val error_server = "Lỗi máy chủ. Vui lòng liên hệ đến admin để xử lí"

fun Context.showToast(message: String = error_server, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun <T> listToJson(vocabularyList: List<T>): String {
    return Gson().toJson(vocabularyList)
}

fun <T> jsonToList(json: String, clazz: Class<T>): List<T> {
    val gson = Gson()
    val listType = TypeToken.getParameterized(List::class.java, clazz).type
    return gson.fromJson(json, listType)
}

fun <T> objectToJson(obj: T): String {
    return Gson().toJson(obj)
}

fun <T> jsonToObject(json: String, clazz: Class<T>): T {
    return Gson().fromJson(json, clazz)
}

fun generateRandomColor(): Color {
    val colors = listOf(
        Color(0xFFFFA726),
        Color(0xFF2196F3) )
    return colors.random()
}
