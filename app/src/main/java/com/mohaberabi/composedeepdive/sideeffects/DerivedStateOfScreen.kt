package com.mohaberabi.composedeepdive.sideeffects

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch


@Composable
fun DerivedStateOfScreen(modifier: Modifier = Modifier) {


    val scope = rememberCoroutineScope()

    val state = rememberLazyListState()


    val showFab by remember {

        derivedStateOf { state.firstVisibleItemIndex != 0 }
    }
    Scaffold(
        floatingActionButton = {

            if (showFab) {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            state.animateScrollToItem(0)
                        }
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = ""
                    )
                }
            }

        },
    ) {

            padding ->


        LazyColumn(
            state = state,
            modifier = modifier.padding(padding),
        ) {

        }
    }
}