package com.peakmain.compose.ui.progress


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.peakmain.compose.basic.BasicColor
/**
 * author ：Peakmain
 * createTime：2025/6/11
 * mail:2726449200@qq.com
 * describe：圆角进度条
 */
@Composable
fun RoundedLinearProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = BasicColor.color_1F401B,
    backgroundColor: Color = BasicColor.color_EBEBF0,
    height: Dp = 4.dp,
    cornerRadius: Dp = 2.dp
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        // 绘制背景
        drawRoundedRect(
            color = backgroundColor,
            size = Size(size.width, size.height),
            cornerRadius = cornerRadius.toPx()
        )

        // 绘制进度条
        if (progress > 0) {
            val progressWidth = size.width * progress.coerceIn(0f, 1f)
            drawRoundedRect(
                color = color,
                topLeft = Offset(0f, 0f),
                size = Size(progressWidth, size.height),
                cornerRadius = cornerRadius.toPx()
            )
        }
    }
}

// 在 DrawScope 中扩展绘制圆角矩形的函数
private fun DrawScope.drawRoundedRect(
    color: Color,
    topLeft: Offset = Offset.Zero,
    size: Size,
    cornerRadius: Float
) {
    drawRoundRect(
        color = color,
        topLeft = topLeft,
        size = size,
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius)
    )
}