package com.example.backendtest.repository.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.backendtest.presentation.viewmodel.TextUpdateViewModel
import com.example.backendtest.presentation.viewmodel.TextViewModel
import com.example.backendtest.remote.dto.TextDto

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun TextScreen(
    viewModel: TextViewModel = hiltViewModel(),
    updateViewModel : TextUpdateViewModel = hiltViewModel()
){
    //val userIds by viewModel.userId.collectAsState()
    val textDtoDL by updateViewModel.uploadState.collectAsState()
    val textList by viewModel.textList.collectAsState() // textList 상태 관찰

    Log.d("textList",textList.toString())
    Log.d("textDtoDL",textDtoDL.toString())


    var userId by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var userNumber by remember { mutableStateOf("") }



    LaunchedEffect(userId) {
        viewModel.getText(userId.toLongOrNull() ?: 0) // ID를 0으로 설정하여 안전하게 처리
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = userId.toString(), // Long을 String으로 변환
            onValueChange = {
                userId = (it.toLongOrNull() ?: "").toString()
            },
            label = { Text(text = "userID") },
            modifier = Modifier.fillMaxWidth()

        )
        // User ID 입력 후 해당하는 TextDto 찾기
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = userNumber.toString(),
            onValueChange = { userNumber = it },
            label = { Text("User Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val inputId = userId.toLongOrNull()
                if (inputId != null) {
                    val matchingUser = textList.find { it.userId == inputId }
                    if (matchingUser != null) {
                        userName = matchingUser.userName ?: ""
                        userNumber = matchingUser.userNumber.toString()
                    } else {
                        userName = ""
                        userNumber = ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("데이터 Get")
        }
        Button(
            onClick = {
                updateViewModel.getUpdateText(
                    textDto = TextDto(
                        userId = userId.toLong(),
                        userName = userName,
                        userNumber = userNumber.toLong()
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("데이터 update")
        }
    }
}