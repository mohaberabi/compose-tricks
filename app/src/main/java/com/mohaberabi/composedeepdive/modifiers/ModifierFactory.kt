package com.mohaberabi.composedeepdive.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateMeasurement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.improvedNegPadding(
    horizontal: Dp = 0.dp,
    vertical: Dp = 0.dp
): Modifier {
    return this.then(NegativePaddingElement(horizontal, vertical))
}

data class NegativePaddingElement(
    private val horizontal: Dp = 0.dp,
    private val vertical: Dp = 0.dp
) : ModifierNodeElement<NegativePaddingNode>() {
    override fun create(): NegativePaddingNode {
        return NegativePaddingNode(horizontal, vertical)
    }

    override fun update(node: NegativePaddingNode) {
        node.horizontal = horizontal
        node.vertical = vertical
    }

    override fun InspectorInfo.inspectableProperties() {
        this.properties["horizontal"] = horizontal
        this.properties["vertical"] = vertical
    }
}

class NegativePaddingNode(
    var horizontal: Dp,
    var vertical: Dp = 0.dp
) : LayoutModifierNode, Modifier.Node(), CompositionLocalConsumerModifierNode {

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val density = currentValueOf(LocalDensity)
        val pxHz = with(density) {
            horizontal.roundToPx()
        }
        val pxVt = with(density) {
            vertical.roundToPx()
        }
        val placeable = measurable.measure(
            constraints.copy(
                minWidth = constraints.minWidth + 2 * pxHz,
                maxWidth = constraints.maxWidth + 2 * pxHz,
                maxHeight = constraints.maxHeight * 2 + pxVt,
                minHeight = constraints.minHeight * 2 + pxVt
            ),
        )

        return layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }

}