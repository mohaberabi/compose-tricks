package com.mohaberabi.composedeepdive.sideeffects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay


@Composable
fun ProductStateScreen(
) {
    val counter by produceState(
        0,
    ) {
        while (true) {
            delay(1000)
            value += 1
        }
    }
}