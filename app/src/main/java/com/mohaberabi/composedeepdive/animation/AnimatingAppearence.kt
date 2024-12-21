package com.mohaberabi.composedeepdive.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme


@Composable
fun AnimatingAppearenceScreen(modifier: Modifier = Modifier) {


    var toggle by remember {

        mutableStateOf(false)
    }

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


        AnimatedVisibility(
            visible = toggle,
            enter = scaleIn(
                animationSpec = keyframes {
                    durationMillis = 5000
                    0.75f at 2500 using EaseInExpo
                    0.25f at 3750 using LinearEasing
                    1f at 5000 using FastOutSlowInEasing
                }
            ) + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            Text(text = "Hello mohab the loser")

        }


    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewAnimatgingAppearenceScreen() {

    ComposeDeepDiveTheme {


        AnimatingAppearenceScreen()
    }
}

