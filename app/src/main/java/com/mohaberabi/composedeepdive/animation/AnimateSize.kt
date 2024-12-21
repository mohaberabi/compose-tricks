package com.mohaberabi.composedeepdive.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme


@Composable
fun AnimateSize(modifier: Modifier = Modifier) {


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



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .height(if (toggle) 200.dp else 500.dp)
                .background(Color.Black)
        )

    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun AnimateSizePreview() {


    ComposeDeepDiveTheme {


        AnimateSize()
    }
}