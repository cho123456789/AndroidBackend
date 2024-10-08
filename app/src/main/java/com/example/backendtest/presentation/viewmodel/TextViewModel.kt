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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TextViewModel @Inject constructor(
    private val getUserTextUseCase: GetUserTextUseCase
) :ViewModel(){

    private val _textList = MutableStateFlow<List<TextDto>>(emptyList())
    val textList: StateFlow<List<TextDto>> get() = _textList

    private val _userName  = MutableStateFlow<String>("")
    val userName : StateFlow<String> = _userName.asStateFlow()

    private val _userNumber  = MutableStateFlow<Long>(1L)
    val userNumber : StateFlow<Long> = _userNumber.asStateFlow()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getText(userId : Long){
        getUserTextUseCase(userId).onEach {resource->
            Log.d("resource",resource.data.toString())
            when(resource){
                is Resource.Success -> {
                    val textResponse = resource.data
                    if(textResponse != null){
                        _textList.value = textResponse
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