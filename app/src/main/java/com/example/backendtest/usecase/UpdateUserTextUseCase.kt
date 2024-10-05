package com.example.backendtest.usecase

import retrofit2.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.backendtest.Common.Resource
import com.example.backendtest.domain.TextGetRespository
import com.example.backendtest.domain.TextUpdateRespository
import com.example.backendtest.remote.dto.TextDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class UpdateUserTextUseCase @Inject constructor(
    private val repository: TextUpdateRespository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(textDto: TextDto): Flow<Resource<TextDto>> = flow {
        try {
            Log.d("userId", textDto.toString())
            val response = repository.updateTextList(textDto)

            if (response.isSuccessful) {
                val textResponse = response.body()
                if (textResponse != null) {
                    emit(Resource.Success(textResponse)) // 성공 상태 emit
                    Log.d("API Response", textResponse.toString())
                } else {
                    emit(Resource.Error("No data available"))
                }
            } else {
                emit(Resource.Error("Error ${response.code()} : ${response.message()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Connection error"))
            Log.e("Error", "HttpException: ${e.message}")
        } catch (e: IOException) {
            emit(Resource.Error("Code error: ${e.message}"))
            Log.e("Error", "IOException: ${e.message}")
        }
    }
}
