package com.example.backendtest.remote.dto

import com.google.gson.annotations.SerializedName

data class TextDto(
    @SerializedName("userId")
    var userId: Long,

    @SerializedName("userName")
    var userName: String?,

    @SerializedName("userNumber")
    var userNumber: Long
)

data class TextResponse(
    @SerializedName("result")
    val data: List<TextDto>
)

fun TextDto.toDto(): TextDto{
    return TextDto(
        userId = userId,
        userName = userName,
        userNumber = userNumber
    )
}
