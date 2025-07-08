package com.peakmain.compose.theme

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.invalidateDraw
import kotlinx.coroutines.launch

/**
 * author ：Peakmain
 * createTime：2025/4/22
 * mail:2726449200@qq.com
 * describe：
 */
open class PkIndication(private val drawColor: Color = Color.Black.copy(0.3f)) :
    IndicationNodeFactory {


    override fun create(interactionSource: InteractionSource): Modifier.Node {
        return PkIndicationNode(interactionSource, drawColor)
    }

    private class PkIndicationNode(
        private val interactionSource: InteractionSource,
        private val drawColor: Color
    ) : Modifier.Node(), DrawModifierNode {


        var isPressed = false
        var isHovered = false
        var isFocused = false

        override fun onAttach() {
            coroutineScope.launch {
                interactionSource.interactions.collect { interaction ->
                    when (interaction) {
                        is PressInteraction.Press -> isPressed = true
                        is PressInteraction.Release -> isPressed = false
                        is PressInteraction.Cancel -> isPressed = false
                        is HoverInteraction.Enter -> isHovered = true
                        is HoverInteraction.Exit -> isHovered = false
                        is FocusInteraction.Focus -> isFocused = true
                        is FocusInteraction.Unfocus -> isFocused = false
                    }
                    // 状态变化时请求重绘
                    invalidateDraw()
                }
            }

        }

        override fun ContentDrawScope.draw() {
            drawContent()
            if (isPressed) {
                drawRect(color = drawColor, size = size)
            } else if (isHovered || isFocused) {
                drawRect(color = drawColor, size = size)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PkIndication) return false
        if (drawColor != other.drawColor) return false
        return true
    }

    override fun hashCode(): Int {
        return drawColor.hashCode()
    }
}