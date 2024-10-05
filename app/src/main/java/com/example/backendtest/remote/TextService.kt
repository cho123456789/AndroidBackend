package com.example.backendtest.remote

import com.example.backendtest.remote.dto.TextDto
import com.example.backendtest.remote.dto.TextResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TextService {
    @GET("api/v1/memberList")
    suspend fun getTextList(
        @Query("userId") userId : Long
    ): Response<TextResponse>

    @POST("api/v1/save")
    suspend fun postTextList(
        @Body textDto: TextDto
    ): Response<TextDto>
}