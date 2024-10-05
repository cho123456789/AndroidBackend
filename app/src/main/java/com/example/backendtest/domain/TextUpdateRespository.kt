package com.example.backendtest.domain

import com.example.backendtest.remote.dto.TextDto
import com.example.backendtest.remote.dto.TextResponse
import retrofit2.Response

interface TextUpdateRespository {
    suspend fun updateTextList(
        textDto: TextDto
    ): Response<TextDto>
}