package com.mohaberabi.composedeepdive.animation

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.DeferredTargetAnimation
import androidx.compose.animation.core.ExperimentalAnimatableApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ApproachLayoutModifierNode
import androidx.compose.ui.layout.ApproachMeasureScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme


@OptIn(ExperimentalLayoutApi::class, ExperimentalAnimatableApi::class)
@Composable
fun LayoutAnimationScreen(
    modifier: Modifier = Modifier,
) {

    var horizontalArrangement by remember {
        mutableStateOf(Arrangement.Start)
    }


    var dpIncrement by remember {
        mutableStateOf(0.dp)
    }


    Scaffold { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(padding)
                .verticalScroll(rememberScrollState()),
        ) {
            AnimatedFlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = horizontalArrangement,
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp + dpIncrement)
                        .animateLayoutChanges(this)
                        .background(Color.Red)
                )
                Box(
                    modifier = Modifier
                        .size(100.dp + dpIncrement)
                        .animateLayoutChanges(this)
                        .background(Color.Green)
                )
                Box(
                    modifier = Modifier
                        .size(100.dp + dpIncrement)
                        .animateLayoutChanges(this)
                        .background(Color.Blue)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                Button(
                    onClick = {
                        horizontalArrangement = Arrangement.Start
                    },
                ) {
                    Text(text = "Start")
                }
                Button(
                    onClick = {
                        horizontalArrangement = Arrangement.End
                    },
                ) {
                    Text(text = "End")
                }

                Button(
                    onClick = {

                        horizontalArrangement = Arrangement.SpaceBetween

                    },
                ) {
                    Text(text = "SpaceBetween")
                }
                Button(
                    onClick = {
                        horizontalArrangement = Arrangement.SpaceAround
                    },
                ) {
                    Text(text = "SpaceAround")
                }
                Button(
                    onClick = {
                        horizontalArrangement = Arrangement.SpaceEvenly
                    },
                ) {
                    Text(text = "SpaceEvenly")
                }
                Button(
                    onClick = {
                        dpIncrement += 10.dp
                    },
                ) {
                    Text(text = "Inc DP")
                }

                Button(
                    onClick = {
                        dpIncrement -= 10.dp
                    },
                ) {
                    Text(text = "Dec DP")
                }


            }

        }
    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimatedFlowRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: @Composable LookaheadScope.() -> Unit,
) {
    LookaheadScope {
        FlowRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = horizontalArrangement,
        ) {
            content()
        }
    }

}


@Preview(
    showBackground = true
)
@Composable
private fun PreviewLayoutAniamtionScreen() {
    ComposeDeepDiveTheme {
        LayoutAnimationScreen()
    }
}