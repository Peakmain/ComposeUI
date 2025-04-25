package com.peakmain.compose.ui.cell

/**
 * author ：Peakmain
 * createTime：2025/4/17
 * mail:2726449200@qq.com
 * describe：单元格
 */
import android.graphics.drawable.Icon
import android.text.TextUtils
import android.view.View
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peakmain.compose.basic.BasicFont
import com.peakmain.compose.basic.BasicSize
import com.peakmain.compose.library.R
import com.peakmain.compose.ui.title.PkTitle
import com.peakmain.compose.ui.title.PkTitleType
/**
 * 通用可自定义右侧内容的标题组件。
 *
 * @param text 左侧显示的文本内容（必填）
 * @param type 标题样式类型（字体大小、字重等），默认 [PkTitleType.BigTitle1]，详见 PkTitleType 文档
 * @param modifier 布局修饰符（如尺寸、边距等），作用于整个单元格容器，默认 [Modifier]
 * @param color 文本颜色，默认 Color(0xFF333333)
 * @param fontStyle 字体风格，如 [FontStyle.Italic]，默认 null
 * @param textAlign 文本对齐方式，如 [TextAlign.End]（右对齐），默认 null（跟随系统）
 * @param overflow 文本溢出处理方式，默认 [TextOverflow.Ellipsis]
 * @param maxLines 文本最大显示行数，默认 1
 * @param style 自定义文本样式，可覆盖 [type] 中的部分属性，默认 [LocalTextStyle.current]
 * @param rightContent 右侧自定义内容（必填），支持任意组合（图标、开关等），通过 [RowScope] 提供灵活布局能力
 */
@Composable
fun PkCell(
    text: String,
    type: PkTitleType = PkTitleType.BigTitle1(),
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF333333),
    fontStyle: FontStyle? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = 1,
    style: TextStyle = LocalTextStyle.current,
    rightContent: @Composable RowScope.() -> Unit
) {
    Row(
        modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PkTitle(text, type, Modifier, color, fontStyle, textAlign, overflow, maxLines, style)
        Row(verticalAlignment = Alignment.CenterVertically) {
            rightContent.invoke(this)
        }
    }
}

/**
 * 通用标题组件
 *
 * @param text 左侧主标题文本内容（必填）
 * @param type 标题样式类型（字体大小、字重），默认 [PkTitleType.BigTitle1]，参考 PkTitleType 文档
 * @param modifier 整体容器修饰符（如边距、背景等），默认 [Modifier]
 * @param color 左侧标题文本颜色，默认 Color(0xFF333333)
 * @param fontStyle 字体风格，如 [FontStyle.Italic]，默认 null
 * @param textAlign 文本对齐方式，默认 null（跟随系统）
 * @param overflow 文本溢出处理方式，默认 [TextOverflow.Ellipsis]
 * @param maxLines 文本最大显示行数，默认 1
 * @param style 自定义文本样式（覆盖 [type] 属性），默认 [LocalTextStyle.current]
 * @param rightText 右侧辅助文本内容（空字符串时不显示），默认 ""
 * @param rightTextColor 右侧辅助文本颜色，默认 Color(0xFF666666)
 * @param rightTextSize 右侧辅助文本字体大小，默认 12.sp
 * @param rightIcon 右侧图标资源 ID（设为 null 时不显示图标），默认 R.drawable.ic_right_arrow
 * @param rightIconSize 右侧图标尺寸，默认 12.dp
 * @param isRightIconRotated 是否启用图标旋转动画（用于展开/收起状态指示），默认 false
 * @param rightClick 点击右侧区域的回调（触发动画和自定义逻辑），默认 null
 */

@Composable
fun PkCell(
    text: String,
    type: PkTitleType = PkTitleType.BigTitle1(),
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF333333),
    fontStyle: FontStyle? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = 1,
    style: TextStyle = LocalTextStyle.current,
    rightText: String = "",
    rightTextColor: Color = Color(0xFF666666),
    rightTextSize: TextUnit = BasicFont.font_12,
    @DrawableRes rightIcon: Int? = R.drawable.ic_right_arrow,
    rightIconSize: Dp = BasicSize.size_12,
    isRightIconRotated: Boolean = false,
    rightClick: (() -> Unit)? = null
) {
    var isRotated by remember { mutableStateOf(false) }
    // 创建一个浮动动画状态，从 0f 度旋转到 360f 度
    val rotation by animateFloatAsState(
        targetValue = if (isRotated) 180f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )
    Row(
        modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PkTitle(text, type, Modifier, color, fontStyle, textAlign, overflow, maxLines, style)
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
            isRotated = !isRotated
            rightClick?.invoke()
        }) {
            if (!TextUtils.isEmpty(rightText)) {
                Text(rightText, color = rightTextColor, fontSize = rightTextSize)
            }
            rightIcon?.let {
                Image(
                    painter = painterResource(it),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(rightTextColor),
                    modifier = Modifier
                        .size(rightIconSize)
                        .rotate(if (isRightIconRotated) rotation else 0f)
                )
            }
        }
    }
}

@Preview
@Composable
fun PkCellPreview() {
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        PkCell(
            "常用功能",
            PkTitleType.BigTitle3(),
            rightText = "展开",
            modifier = Modifier.padding(horizontal = 18.dp),
            color = Color(0xFF14401B)
        )
    }


}

