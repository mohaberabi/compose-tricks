package com.mohaberabi.composedeepdive.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme


inline fun Modifier.applyIf(
    condition: Boolean,
    other: Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        this.then(other())
    } else this
}


@Composable
fun TestMe(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .applyIf(true) {
                background(Color.Green)
            }
    ) {

    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun PrevTestMe() {
    ComposeDeepDiveTheme {
        TestMe()
    }
}