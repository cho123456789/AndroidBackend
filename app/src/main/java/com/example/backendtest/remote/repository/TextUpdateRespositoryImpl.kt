package com.example.backendtest.remote.repository

import com.example.backendtest.domain.TextGetRespository
import com.example.backendtest.domain.TextUpdateRespository
import com.example.backendtest.remote.TextService
import com.example.backendtest.remote.dto.TextDto
import com.example.backendtest.remote.dto.TextResponse
import retrofit2.Response
import javax.inject.Inject

class TextUpdateRespositoryImpl @Inject constructor(
    private val api : TextService,
):TextUpdateRespository
{
    override suspend fun updateTextList(
        textDto: TextDto
    ): Response<TextDto> {
        return api.postTextList(textDto)
    }

}