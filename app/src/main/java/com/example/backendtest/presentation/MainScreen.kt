package com.example.backendtest.repository.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MainScreen(){
    TextScreen()
}