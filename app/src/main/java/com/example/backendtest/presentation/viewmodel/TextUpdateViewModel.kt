package com.example.backendtest.presentation.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.backendtest.Common.Resource
import com.example.backendtest.presentation.TextListState
import com.example.backendtest.remote.dto.TextDto
import com.example.backendtest.remote.dto.toDto
import com.example.backendtest.usecase.GetUserTextUseCase
import com.example.backendtest.usecase.UpdateUserTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TextUpdateViewModel @Inject constructor(
    private val updateUserTextUseCase: UpdateUserTextUseCase
) :ViewModel(){


    private val _uploadState = MutableStateFlow<Resource<TextDto>>(Resource.Loading())
    val uploadState: StateFlow<Resource<TextDto>> = _uploadState.asStateFlow()


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getUpdateText(textDto: TextDto){
        updateUserTextUseCase(textDto).onEach {resource->
            Log.d("resource",resource.data.toString())
            when(resource){
                is Resource.Success -> {
                    val textResponse = resource.data
                    if(textResponse != null){
                       _uploadState.value = resource
                    }
                }
                is Resource.Error -> {
                    TextListState(
                        error = resource.message ?: "unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    TextListState(
                        isLoading = resource.message ?: "data Loading.. "
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}