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
/**
 * 通用按钮组件，支持自定义样式、交互行为与内容布局。
 *
 * @param onClick 按钮点击事件回调（必填）
 * @param modifier 布局修饰符（如尺寸、边距、背景等），默认 [Modifier]
 * @param enabled 是否启用按钮，默认 true；禁用时自动降低透明度并屏蔽点击事件
 * @param interactionSource 按钮交互状态源（如按压、悬停），默认 `remember { MutableInteractionSource() }`
 * @param elevation 按钮阴影效果，默认 [ButtonDefaults.elevation]；设置为 null 可移除阴影
 * @param shape 按钮形状，默认 [PkTheme.shapes.small]，如 `RoundedCornerShape(4.dp)`
 * @param border 按钮边框，默认 null，如 `BorderStroke(1.dp, Color.Gray)`
 * @param colors 按钮颜色配置，默认 [PkButtonDefault.buttonColors]，包含默认、禁用、按压等状态颜色
 * @param contentPadding 内容内边距，默认 [PkButtonDefault.BigContentPadding]，即 `PaddingValues(horizontal = 16.dp, vertical = 8.dp)`
 * @param content 按钮内部内容（必填），支持 [RowScope]，可自由组合文本、图标等
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
    colors: ButtonColors = PkButtonDefault.buttonColors(interactionSource),
    contentPadding: PaddingValues = PkButtonDefault.BigContentPadding,
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