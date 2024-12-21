package com.mohaberabi.composedeepdive.animation

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.DeferredTargetAnimation
import androidx.compose.animation.core.ExperimentalAnimatableApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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


@OptIn(ExperimentalLayoutApi::class)
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimatedFlowRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: @Composable FlowRowScope.() -> Unit,
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


class AnimatedLayoutChanges @OptIn(ExperimentalAnimatableApi::class) constructor(
    private val scope: LookaheadScope,
    private val positionAnimation: DeferredTargetAnimation<IntOffset, AnimationVector2D>,
    private val sizeAnimation: DeferredTargetAnimation<IntSize, AnimationVector2D>,
) : ApproachLayoutModifierNode, Modifier.Node() {


    @OptIn(ExperimentalAnimatableApi::class)
    override fun Placeable.PlacementScope.isPlacementApproachInProgress(
        lookaheadCoordinates: LayoutCoordinates,
    ): Boolean {
        val targetOffset = with(scope) {
            lookaheadScopeCoordinates.localLookaheadPositionOf(lookaheadCoordinates)
        }
        positionAnimation.updateTarget(targetOffset.round(), coroutineScope, tween(1500))
        return !positionAnimation.isIdle
    }

    @OptIn(ExperimentalAnimatableApi::class)
    override fun isMeasurementApproachInProgress(lookaheadSize: IntSize): Boolean {
        sizeAnimation.updateTarget(lookaheadSize, coroutineScope, tween(1500))
        return !sizeAnimation.isIdle
    }

    override fun ApproachMeasureScope.approachMeasure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        TODO("Not yet implemented")
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