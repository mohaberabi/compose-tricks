package com.mohaberabi.composedeepdive.basics.windowsize

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.window.core.layout.WindowWidthSizeClass
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme


@Composable
fun WindowSizeClassScreen(modifier: Modifier = Modifier) {


    val windowSize = currentWindowAdaptiveInfo().windowSizeClass



    when (windowSize.windowWidthSizeClass) {
        WindowWidthSizeClass.EXPANDED -> {}
        WindowWidthSizeClass.COMPACT -> {}
        WindowWidthSizeClass.MEDIUM -> {}

    }
}


@PreviewScreenSizes
@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun PreviewWindowSizeScreen() {

    ComposeDeepDiveTheme {

        WindowSizeClassScreen()
    }
}