package com.example.backendtest.usecase

import retrofit2.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.backendtest.Common.Resource
import com.example.backendtest.domain.TextGetRespository
import com.example.backendtest.remote.dto.TextDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class GetUserTextUseCase @Inject constructor(
    private val repository : TextGetRespository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(userId: Long) : Flow<Resource<List<TextDto>>> =
        flow{
        try{
            Log.e("userId", userId.toString())
            val response = repository.getTextList(userId)
            Log.e("API Error", "Error ${response.code()} : ${response.message()}")
            Log.d("API Response",response.raw().toString())
            if(response.isSuccessful){
                val textResponse = response.body()
                if(textResponse != null){
                    Resource.Success(textResponse.data).let { emit(it) } // 성공 상태 emit
                    Log.d("info",textResponse.toString())
                }else{
                    emit(Resource.Error("No data available"))
                }
            }
            else{
                emit(Resource.Error("Error ${response.code()} : ${response.message()}"))
            }
        }catch (e : HttpException){
            emit(Resource.Error("Connection error"))
        }catch (e : IOException){
            emit(Resource.Error("Code error"))
            emit(Resource.Error("Code error: ${e.message}"))
            Log.e("Error", "IOException: ${e.message}")
        }
    }
}