package com.peakmain.compose.theme

import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope

/**
 * author ：Peakmain
 * createTime：2025/4/22
 * mail:2726449200@qq.com
 * describe：透明
 */
object PkTranslateIndication : PkIndication() {

        private class DefaultDebugIndicationInstance(
            private val isPressed: State<Boolean>,
            private val isHovered: State<Boolean>,
            private val isFocused: State<Boolean>,
        ) : IndicationInstance {
            override fun ContentDrawScope.drawIndication() {
                drawContent()
                if (isPressed.value) {
                    drawRect(color = Color.Transparent, size = size)
                } else if (isHovered.value || isFocused.value) {
                    drawRect(color = Color.Transparent, size = size)
                }
            }
        }

        @Composable
        override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
            val isPressed = interactionSource.collectIsPressedAsState()
            val isHovered = interactionSource.collectIsHoveredAsState()
            val isFocused = interactionSource.collectIsFocusedAsState()
            return remember(interactionSource) {
                DefaultDebugIndicationInstance(isPressed, isHovered, isFocused)
            }
        }
    }