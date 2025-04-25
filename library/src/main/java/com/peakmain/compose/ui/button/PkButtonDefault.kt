package com.peakmain.compose.ui.button

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.peakmain.compose.theme.PkTheme

/**
 * author ：Peakmain
 * createTime：2025/4/10
 * mail:2726449200@qq.com
 * describe：
 */
object PkButtonDefault {
    private val ButtonBigHorizontalPadding = 24.dp
    private val ButtonBigVerticalPadding = 11.dp

    private val ButtonSmallHorizontalPadding = 12.dp
    private val ButtonSmallVerticalPadding = 5.dp

    /**
     * The default content padding used by [Button]
     */
    val BigContentPadding = PaddingValues(
        start = ButtonBigHorizontalPadding,
        top = ButtonBigVerticalPadding,
        end = ButtonBigHorizontalPadding,
        bottom = ButtonBigVerticalPadding
    )

    val smallContentPadding = PaddingValues(
        start = ButtonSmallHorizontalPadding,
        top = ButtonSmallVerticalPadding,
        end = ButtonSmallHorizontalPadding,
        bottom = ButtonSmallVerticalPadding
    )

    @Composable
    fun buttonColors(
        backgroundColor: Color = PkTheme.colors.primary,
        contentColor: Color = PkTheme.colors.contentColorFor(backgroundColor),
        disabledBackgroundColor: Color = Color(0xFFD4D4D5),
        disabledContentColor: Color = PkTheme.colors.onPrimary
    ): ButtonColors = PkButtonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledContentColor = disabledContentColor,
        interactionSource = remember { MutableInteractionSource() }
    )

    @Composable
    fun transparentColor(
        backgroundColor: Color = Color.Transparent,
        contentColor: Color = Color(0xFF1F4D1B),
        disabledBackgroundColor: Color = Color(0xFFD4D4D5),
        disabledContentColor: Color = PkTheme.colors.onPrimary
    ): ButtonColors = PkButtonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledContentColor = disabledContentColor,
        interactionSource = remember { MutableInteractionSource() }
    )

}

@Immutable
class PkButtonColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val pressedContentColor: Color = contentColor.copy(alpha = 0.7f),
    private val disabledBackgroundColor: Color,
    private val disabledContentColor: Color,
    private val interactionSource: InteractionSource,
    private val pressedBackgroundColor: Color = backgroundColor.copy(alpha = 0.7f),
) : ButtonColors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        val isPressed by interactionSource.collectIsPressedAsState()
        val color = when {
            !enabled -> disabledBackgroundColor
            isPressed -> pressedBackgroundColor
            else -> backgroundColor
        }
        return rememberUpdatedState(color)
    }

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        val isPressed by interactionSource.collectIsPressedAsState()
        val color = when {
            !enabled -> disabledContentColor
            isPressed -> pressedContentColor
            else -> contentColor
        }
        return rememberUpdatedState(color)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PkButtonColors) return false

        return backgroundColor == other.backgroundColor &&
                pressedBackgroundColor == other.pressedBackgroundColor &&
                contentColor == other.contentColor &&
                pressedContentColor == other.pressedContentColor &&
                disabledBackgroundColor == other.disabledBackgroundColor &&
                disabledContentColor == other.disabledContentColor
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + pressedBackgroundColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + pressedContentColor.hashCode()
        result = 31 * result + disabledBackgroundColor.hashCode()
        result = 31 * result + disabledContentColor.hashCode()
        return result
    }
}