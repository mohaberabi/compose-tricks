package com.mohaberabi.composedeepdive.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun AnimateableScreen(modifier: Modifier = Modifier) {


    val scope = rememberCoroutineScope()
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val step1Offset = remember {

        Animatable(
            initialValue = 0f,
        )
    }
    val step2Offset = remember {

        Animatable(
            initialValue = 0f,
        )
    }
    val step3Offset = remember {
        Animatable(
            initialValue = 0f,
        )
    }
    Column(
        modifier = modifier.fillMaxSize(),
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .onSizeChanged {
                    size = it
                }
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset {
                        IntOffset(
                            x = ((size.width - 100.dp.roundToPx()) * step1Offset.value).roundToInt(),
                            y = ((size.height - 100.dp.roundToPx()) * step1Offset.value).roundToInt(),
                        )
                    }
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset {
                        IntOffset(
                            x = ((size.width - 100.dp.roundToPx()) * step2Offset.value).roundToInt(),
                            y = ((size.height - 100.dp.roundToPx()) * step1Offset.value).roundToInt(),
                        )
                    }
                    .graphicsLayer {
                        val newScale = 1 - (step2Offset.value * 0.25f)
                        scaleX = newScale
                        scaleY = newScale
                    }
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset {
                        IntOffset(
                            x = ((size.width - 100.dp.roundToPx()) * step1Offset.value).roundToInt(),
                            y = ((size.height - 100.dp.roundToPx()) * step2Offset.value).roundToInt(),
                        )
                    }
                    .graphicsLayer {
                        val newScale = 1 - (step2Offset.value * 0.5f)
                        scaleX = newScale
                        scaleY = newScale
                    }
                    .background(Color.Blue)
            )
        }
        Button(
            onClick = {
                scope.launch {
                    step1Offset.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(
                            durationMillis = 5000,
                            easing = EaseInCubic
                        )
                    )
                    step2Offset.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(
                            durationMillis = 5000,
                            easing = EaseInCubic
                        )
                    )


                }


            },
        ) {
            Text(text = "Start")
        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewAnimateableScreen() {

    ComposeDeepDiveTheme {


        AnimateableScreen()
    }
}