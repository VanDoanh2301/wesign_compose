package com.example.wesign.utils

import android.content.Context
import android.widget.Toast

const val error_server = "Lỗi máy chủ. Vui lòng liên hệ đến admin để xử lí"

fun Context.showToast(message: String = error_server, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}