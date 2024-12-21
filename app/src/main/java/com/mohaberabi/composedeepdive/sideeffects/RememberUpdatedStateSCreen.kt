package com.mohaberabi.composedeepdive.sideeffects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay


@Composable
fun LaunchedAnimation(
    snackBarHostState: SnackbarHostState,
    userName: String,
) {


    val updatedUserName by rememberUpdatedState(newValue = userName)
    LaunchedEffect(Unit) {
        delay(5000L)
        snackBarHostState.showSnackbar("Welcome $updatedUserName")
    }

}

@Composable
fun RememberUpdatedStateScreen(

) {


    val snackBarHostState = remember {
        SnackbarHostState()
    }

    var usrename by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = Unit) {
        delay(3000)
        usrename = "Mohab The best loser i have ver known "
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) {

            padding ->
        Column(
            modifier = Modifier.padding(padding),
        ) {
            TextField(
                value = usrename,
                onValueChange = { usrename = it },
            )

            LaunchedAnimation(
                snackBarHostState = snackBarHostState,
                userName = usrename
            )
        }
    }
}