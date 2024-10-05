package com.example.backendtest.domain

import com.example.backendtest.remote.dto.TextResponse
import retrofit2.Response

interface TextGetRespository {
    suspend fun getTextList(
        userId : Long
    ): Response<TextResponse>
}