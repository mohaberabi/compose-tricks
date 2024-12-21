package com.mohaberabi.composedeepdive.animation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme


@Composable
fun AnimateXAsStateScreen(modifier: Modifier = Modifier) {

    var toggle by remember {

        mutableStateOf(false)
    }


    val ratio by animateFloatAsState(
        targetValue = if (toggle) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ),
        label = "RatioAnimation"
    )

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Button(
            onClick = {
                toggle = !toggle
            },
        ) {
            Text(text = "Toggle me loser")
        }



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        rotationZ = 405f * ratio
                    }
                    .size(100.dp)
                    .background(Color.Blue),
            )
        }
    }

}


@Preview(
    showBackground = true,
)
@Composable
private fun AnimateXAsStatePreview() {

    ComposeDeepDiveTheme {


        AnimateXAsStateScreen()
    }
}