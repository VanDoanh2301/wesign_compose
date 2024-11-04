package com.example.wesign.domain.mapper

import com.example.wesign.data.model.response.HostResponse


fun <T, R> HostResponse<T>.toDomain(transform: (T) -> R): HostResponse<out R> {
    return if (this.data != null) {
        HostResponse(
            code = this.code,
            data = transform(this.data),
            message = this.message
        )
    } else {
        HostResponse(
            code = this.code,
            data = null,
            message = this.message
        )
    }
}
