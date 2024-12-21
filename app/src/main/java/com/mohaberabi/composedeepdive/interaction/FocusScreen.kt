package com.mohaberabi.composedeepdive.interaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp


@Composable
fun FocusScreen(modifier: Modifier = Modifier) {

    val first = remember {
        FocusRequester()
    }
    val second = remember {
        FocusRequester()
    }
    val third = remember {
        FocusRequester()
    }
    val fourth = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current
    LaunchedEffect(
        key1 = Unit,
    ) {
        first.requestFocus()
    }
    Scaffold { padding ->
        Column(
            modifier = modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            TextField(
                modifier = modifier.focusRequester(first),
                value = "",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)

                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            TextField(
                value = "",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            TextField(
                value = "",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)

                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            TextField(
                value = "",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)

                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
        }
    }
}