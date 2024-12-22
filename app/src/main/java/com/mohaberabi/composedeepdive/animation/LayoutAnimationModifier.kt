package com.mohaberabi.composedeepdive.animation

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.DeferredTargetAnimation
import androidx.compose.animation.core.ExperimentalAnimatableApi
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ApproachLayoutModifierNode
import androidx.compose.ui.layout.ApproachMeasureScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.round


@OptIn(ExperimentalAnimatableApi::class)
data class AnimateLayoutChangesElement(
    private val scope: LookaheadScope,
    private val positionAnimation: DeferredTargetAnimation<IntOffset, AnimationVector2D>,
    private val sizeAnimation: DeferredTargetAnimation<IntSize, AnimationVector2D>
) : ModifierNodeElement<AnimatedLayoutChangesNode>() {
    override fun create(): AnimatedLayoutChangesNode {
        return AnimatedLayoutChangesNode(
            scope = scope,
            positionAnimation = positionAnimation,
            sizeAnimation = sizeAnimation
        )
    }

    override fun update(node: AnimatedLayoutChangesNode) = Unit

}

@OptIn(ExperimentalAnimatableApi::class)
class AnimatedLayoutChangesNode(
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

    @OptIn(ExperimentalAnimatableApi::class)

    override fun ApproachMeasureScope.approachMeasure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val (width, height) = sizeAnimation.updateTarget(
            lookaheadSize,
            coroutineScope,
            tween(1500)
        )
        val animatedConstraints = Constraints.fixed(width, height)
        val placeable = measurable.measure(animatedConstraints)
        return with(scope) {
            layout(placeable.width, placeable.height) {
                coordinates?.let {
                    val targetOffset = lookaheadScopeCoordinates.localLookaheadPositionOf(it)
                    val animatedOffset = positionAnimation.updateTarget(
                        targetOffset.round(),
                        coroutineScope,
                        tween(1500)
                    )
                    val currentOffset = lookaheadScopeCoordinates.localPositionOf(
                        sourceCoordinates = it,
                    )
                    val (x, y) = animatedOffset - currentOffset.round()
                    placeable.place(x, y)
                } ?: placeable.place(0, 0)
            }
        }
    }

}

@OptIn(ExperimentalAnimatableApi::class)

fun Modifier.animateLayoutChanges(
    scope: LookaheadScope,
    positionAnimation: DeferredTargetAnimation<IntOffset, AnimationVector2D> = DeferredTargetAnimation(
        IntOffset.VectorConverter
    ),
    sizeAnimation: DeferredTargetAnimation<IntSize, AnimationVector2D> = DeferredTargetAnimation(
        IntSize.VectorConverter
    ),
): Modifier {
    return then(
        AnimateLayoutChangesElement(
            scope = scope,
            positionAnimation = positionAnimation,
            sizeAnimation = sizeAnimation
        )
    )
}