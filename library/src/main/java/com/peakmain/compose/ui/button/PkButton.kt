package com.peakmain.compose.ui.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.peakmain.compose.theme.PkTheme

/**
 * author ：Peakmain
 * createTime：2025/4/10
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun PkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = PkTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = PkButtonDefault.buttonColors(),
    contentPadding: PaddingValues = PkButtonDefault.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick,
        modifier,
        enabled,
        interactionSource,
        elevation,
        shape,
        border,
        colors,
        contentPadding,
    ) {
        content()
    }
}