package com.example.backendtest.repository.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.backendtest.presentation.viewmodel.TextViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun TextScreen(
    viewModel: TextViewModel = hiltViewModel()
){
    val userIds by viewModel.userId.collectAsState()
    val userNumberIds by viewModel.userNumber.collectAsState()
    val userNameIds by viewModel.userName.collectAsState()

    val userIdT = userIds
    val userNameT = userNameIds
    val userNumberT = userNumberIds

    var userId by remember { mutableStateOf(1L) }
    var userName by remember { mutableStateOf("") }
    var userNumber by remember { mutableStateOf(1L) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = userId.toString(), // Long을 String으로 변환
            onValueChange = {
                userId = it.toLongOrNull() ?: 1L // 입력된 문자열을 Long으로 변환, 변환 실패 시 기본값 1L
            },            label = { Text(text = "userID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = userNumber.toString(), // Long을 String으로 변환
            onValueChange = {
                userNumber = it.toLongOrNull() ?: 1L // 입력된 문자열을 Long으로 변환, 변환 실패 시 기본값 1L
            },            label = { Text("User Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.getText(userId,userName,userNumber)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}