package com.example.backendtest.usecase

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.backendtest.Common.Resource
import com.example.backendtest.domain.TextRespository
import com.example.backendtest.remote.dto.TextDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.w3c.dom.Text
import java.io.IOException
import javax.inject.Inject

class GetUserTextUseCase @Inject constructor(
    private val repository : TextRespository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(userId: Long, userName : String, userNumber : Long) : Flow<Resource<TextDto>> =
        flow{
        emit(Resource.Loading())
        try{
            val response = repository.getTextList(userId,userName,userNumber)
            Log.d("API Response",response.raw().toString())

            if(response.isSuccessful){
                val info = response.body()

                if(info != null){
                    emit(Resource.Success(info))
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
        }
    }
}