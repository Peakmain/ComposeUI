package com.peakmain.compose.ui.cell

/**
 * author ：Peakmain
 * createTime：2025/4/17
 * mail:2726449200@qq.com
 * describe：单元格
 */
import BasicFont
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
import com.peakmain.compose.basic.BasicSize
import com.peakmain.compose.library.R
import com.peakmain.compose.ui.title.PkTitle
import com.peakmain.compose.ui.title.PkTitleType

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

