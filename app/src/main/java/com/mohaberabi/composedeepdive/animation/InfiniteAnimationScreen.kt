package com.mohaberabi.composedeepdive.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme


@Composable
fun InfiniteAnimationScreen(modifier: Modifier = Modifier) {


    val transition = rememberInfiniteTransition(label = "InfiniteTransition")


    val ratio by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "RatioAnimate"
    )

val color by transition.animateColor(
    initialValue = Color.Red,
    targetValue = Color.Blue,
    animationSpec = infiniteRepeatable(
        animation = tween(3000),
        repeatMode = RepeatMode.Reverse
    ),
    label = "ColorAnimate"
)


    Box(
        modifier = Modifier
            .graphicsLayer {
                rotationZ = ratio * 360f
                scaleX = ratio
                scaleY = ratio
            }
            .size(100.dp)
            .background(color),
    )


}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewInfiniteScreen() {


    ComposeDeepDiveTheme {

        InfiniteAnimationScreen()
    }
}