package com.mohaberabi.composedeepdive.optimizations

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.launch


@Composable
fun DeferringStateReads(modifier: Modifier = Modifier) {


    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val fabOffset by remember {
        derivedStateOf {
            val percentage = (1f - (scrollState.firstVisibleItemIndex / 10f))
            (percentage * 100.dp).coerceIn(0.dp, 100.dp)
        }
    }
    val items by remember {
        mutableStateOf((1..100).map { "Item ${it}" })
    }
    Scaffold(
        floatingActionButton = {
            AnimatedFab(
                offset = { fabOffset },
                onClick = {
                    scope.launch {
                        scrollState.animateScrollToItem(0)
                    }
                },
            )
        }
    ) {

            padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(items) { item ->
                Text(text = item)
            }

        }

    }


}


@Composable
fun AnimatedFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    offset: () -> Dp,
) {

    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.offset {
            IntOffset(
                x = 0,
                y = with(density) { offset().roundToPx() })
        },
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "arrowup"
        )
    }
}