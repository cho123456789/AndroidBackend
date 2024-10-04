package com.example.backendtest.remote.dto

import com.google.gson.annotations.SerializedName

data class TextDto(
    @SerializedName("serverId")
    var userId : Long,
    @SerializedName("userName")
    var userName : String,
    @SerializedName("userNumber")
    var userNumber: Long
)

fun TextDto.toDto(): TextDto{
    return TextDto(
        userId = userId,
        userName = userName,
        userNumber = userNumber
    )
}
