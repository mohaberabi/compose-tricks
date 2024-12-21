package com.mohaberabi.composedeepdive.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
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
import androidx.compose.animation.togetherWith
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
fun AnimateSwapping(modifier: Modifier = Modifier) {
    var toggle by remember {

        mutableStateOf(true)
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


        AnimatedContent(
            targetState = toggle,
            transitionSpec = {
                (slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(3000)
                )) togetherWith slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(2000)
                )
            }
        ) { togg ->

            if (togg) {
                Text(text = "Hello mohab the loser")

            } else {
                Text(text = "Bye Bye  mohab the loser")

            }
        }


    }

}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewAniamteSwapping() {


    ComposeDeepDiveTheme {

        AnimateSwapping()
    }
}