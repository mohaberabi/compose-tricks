package com.mohaberabi.composedeepdive.optimizations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@Composable
fun CounterScreenRoot(modifier: Modifier = Modifier) {

    var counter by remember {
        mutableIntStateOf(0)
    }

    CounterScreen(
        modifier = modifier,
        counter = counter,
        onClick = {
            counter++
        },
    )
}


@Composable
fun CounterScreen(
    counter: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Counter : $counter")
        Button(
            onClick = onClick,
        ) {
            Text(text = "Click Me ")
        }
    }

}

