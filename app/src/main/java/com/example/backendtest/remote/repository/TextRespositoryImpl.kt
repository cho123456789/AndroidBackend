package com.example.backendtest.remote.repository

import com.example.backendtest.domain.TextRespository
import com.example.backendtest.remote.TextService
import com.example.backendtest.remote.dto.TextDto
import retrofit2.Response
import javax.inject.Inject

class TextRespositoryImpl @Inject constructor(
    private val api : TextService,
):TextRespository
{
    override suspend fun getTextList(
        userId: Long,
        userName: String,
        userNumber: Long
    ): Response<TextDto> {
        return api.getTextList(userId,userName,userNumber)
    }

}