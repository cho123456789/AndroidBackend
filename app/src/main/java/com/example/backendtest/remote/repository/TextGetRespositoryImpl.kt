package com.example.backendtest.remote.repository

import com.example.backendtest.domain.TextGetRespository
import com.example.backendtest.remote.TextService
import com.example.backendtest.remote.dto.TextResponse
import retrofit2.Response
import javax.inject.Inject

class TextGetRespositoryImpl @Inject constructor(
    private val api : TextService,
):TextGetRespository
{
    override suspend fun getTextList(
        userId: Long
    ): Response<TextResponse> {
        return api.getTextList(userId)
    }

}