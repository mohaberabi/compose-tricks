package com.mohaberabi.composedeepdive.layouts


import android.graphics.pdf.PdfDocument.Page
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMaxOfOrNull
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme


@Composable
fun SubComposePagedRow(
    modifier: Modifier = Modifier,
    page: Int = 0,
    content: @Composable () -> Unit = {}
) {

    SubcomposeLayout(
        modifier = modifier,
    ) { constraints ->

        val pages = mutableListOf<List<Placeable>>()
        var currentPage = mutableListOf<Placeable>()
        var currentPageWidth = 0

        val measurables = subcompose("content", content)
        var i = 0
        for (measurable in measurables) {
            val placeable = measurable.measure(constraints)
            i++
            if (currentPageWidth + placeable.width > constraints.maxWidth) {
                if (pages.size == page + 1) {
                    break
                }
                pages.add(currentPage)
                currentPage = mutableListOf()
                currentPageWidth = 0
            }
            currentPage.add(placeable)
            currentPageWidth += placeable.width
        }

        Log.d("SubComposePagedRow","You had ${measurables.size} Composables , But Rendered Only $i")
        if (currentPage.isNotEmpty()) {
            pages.add(currentPage)
        }
        val pageItems = pages.getOrNull(page) ?: emptyList()
        layout(constraints.maxWidth, constraints.maxHeight) {
            var xOffset = 0
            pageItems.fastForEach { placeable ->
                placeable.place(xOffset, 0)
                xOffset += placeable.width
            }
        }

    }

}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewSubComposePagedRow() {


    ComposeDeepDiveTheme {
        SubComposePagedRow(
            page = 0
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp)
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .width(75.dp)
                    .height(100.dp)

                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .width(37.dp)
                    .height(50.dp)
                    .background(Color.Yellow)
            )
            Box(
                modifier = Modifier
                    .width(17.dp)
                    .height(25.dp)
                    .background(Color.Black)
            )
        }
    }
}