package com.mohaberabi.composedeepdive.layouts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.round
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMapIndexedNotNull
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme
import kotlin.math.roundToInt


data class MinMapItem(
    val title: String,
    val percentageOffset: Offset,
)

data class ProcessedMindMapItem(
    val placeable: Placeable,
    val finalX: Int,
    val finalY: Int
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MindMappingScreen(
    modifier: Modifier = Modifier,
    itemModifier: Modifier = Modifier,
    items: List<MinMapItem> = listOf(),
    mindMapOffset: IntOffset = IntOffset.Zero,
    onDrag: (delta: IntOffset) -> Unit = {},
) {
    LazyLayout(
        modifier = modifier.draggable2D(
            rememberDraggable2DState {
                onDrag(it.round())
            },
        ),
        itemProvider = {
            object : LazyLayoutItemProvider {
                override val itemCount: Int
                    get() = items.size

                @Composable
                override fun Item(index: Int, key: Any) {
                    Text(
                        modifier = itemModifier
                            .widthIn(min = 50.dp, max = 150.dp)
                            .heightIn(min = 50.dp, max = 75.dp)
                            .border(width = 2.dp, color = Color.LightGray)
                            .padding(16.dp),
                        text = items[index].title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

        },
        prefetchState = LazyLayoutPrefetchState(),
    ) { constraints ->

        val maxHeight = constraints.maxHeight
        val maxWidth = constraints.maxWidth
        val visible = IntRect(
            left = 0,
            top = 0,
            right = maxWidth,
            bottom = maxHeight,
        )

        val visibleItems = items.fastMapIndexedNotNull { index, minMapItem ->

            val finalX =
                (minMapItem.percentageOffset.x * maxWidth + maxWidth / 2 + mindMapOffset.x).roundToInt()

            val finalY =
                (minMapItem.percentageOffset.y * maxHeight + maxHeight / 2 + mindMapOffset.y).roundToInt()


            val maxItemWidth = 150.dp.roundToPx()
            val maxItemHeight = 75.dp.roundToPx()
            val bounds = IntRect(
                left = finalX - maxHeight / 2,
                top = finalY - maxHeight / 2,
                right = finalX + 3 * (maxItemWidth) / 2,
                bottom = finalY + 3 * (maxItemHeight / 2)
            )

            if (visible.overlaps(bounds)) {
                val placeable = measure(
                    index = index,
                    constraints = Constraints()
                ).first()
                ProcessedMindMapItem(
                    placeable = placeable,
                    finalX = finalX,
                    finalY = finalY
                )
            } else {
                null
            }
        }
        layout(
            maxHeight,
            maxWidth,
        ) {
            visibleItems.fastForEach { processed ->
                processed.placeable.place(
                    x = processed.finalX,
                    y = processed.finalY
                )
            }

        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewMindMapScreen() {
    ComposeDeepDiveTheme {
        MindMappingScreen()
    }
}