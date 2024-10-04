package com.example.backendtest.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.backendtest.Common.Resource
import com.example.backendtest.presentation.TextListState
import com.example.backendtest.usecase.GetUserTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TextViewModel @Inject constructor(
    private val getUserTextUseCase: GetUserTextUseCase
) :ViewModel(){

    private val _userId  = MutableStateFlow<Long>(1L)
    val userId : StateFlow<Long> = _userId.asStateFlow()

    private val _userName  = MutableStateFlow<String>("")
    val userName : StateFlow<String> = _userName.asStateFlow()

    private val _userNumber  = MutableStateFlow<Long>(1L)
    val userNumber : StateFlow<Long> = _userNumber.asStateFlow()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getText(userId : Long, userName : String, userNumber :Long){
        getUserTextUseCase(userId,userName,userNumber).onEach {resource->
            when(resource){
                is Resource.Success -> {
                    val textResponse = resource.data
                    if(textResponse != null){
                        _userId.value = textResponse.userId
                        _userName.value = textResponse.userName
                        _userNumber.value = textResponse.userNumber
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