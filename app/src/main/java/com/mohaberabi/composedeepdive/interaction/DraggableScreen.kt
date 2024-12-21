package com.mohaberabi.composedeepdive.interaction

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragabbleScreen(modifier: Modifier = Modifier) {


    var offset by remember {

        mutableStateOf(Offset.Zero)
    }


    Scaffold { padding ->

        Column(
            modifier = modifier
                .padding(padding)
                .padding(20.dp)
        ) {


            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset {
                        offset.round()
                    }
                    .draggable2D(state = rememberDraggable2DState {
                        offset+=it
                    }).clip(CircleShape)
                    .background(Color.Red)


            )

        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewDragScreen() {

    ComposeDeepDiveTheme {

        DragabbleScreen()
    }
}