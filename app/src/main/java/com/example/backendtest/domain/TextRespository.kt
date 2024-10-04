package com.example.backendtest.domain

import com.example.backendtest.remote.dto.TextDto
import retrofit2.Response

interface TextRespository {
    suspend fun getTextList(
        userId : Long,
        userName : String,
        userNumber :Long
    ): Response<TextDto>
}