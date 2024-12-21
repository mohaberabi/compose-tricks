package com.mohaberabi.composedeepdive.modifiers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme

@Composable
fun Modifier.negPadding(
    horizontal: Dp,
    vertical: Dp = 0.dp
): Modifier {
    val density = LocalDensity.current
    val pxHz = with(density) {
        horizontal.roundToPx()
    }
    val pxVt = with(density) {
        vertical.roundToPx()
    }
    return layout { measurable, constriants ->
        val placeable = measurable.measure(
            constriants.copy(
                minWidth = constriants.minWidth + 2 * pxHz,
                maxWidth = constriants.maxWidth + 2 * pxHz,
                maxHeight = constriants.maxHeight * 2 + pxVt,
                minHeight = constriants.minHeight * 2 + pxVt
            ),
        )
        layout(
            placeable.measuredWidth,
            placeable.measuredHeight
        ) { placeable.place(0, 0) }
    }
}


@Composable
fun SomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(20.dp)) {

        Text(text = "SOME TEXT")

        HorizontalDivider(
            modifier = Modifier.negPadding(horizontal = 16.dp)
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewSomeScreen() {
    ComposeDeepDiveTheme {
        SomeScreen()
    }
}