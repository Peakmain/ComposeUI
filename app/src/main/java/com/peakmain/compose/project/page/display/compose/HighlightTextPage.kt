package com.peakmain.compose.project.page.display.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.peakmain.compose.project.constants.PkColor
import com.peakmain.compose.ui.text.PkHighlightText
import com.peakmain.compose.ui.title.PkNavBar

/**
 * author ：Peakmain
 * createTime：2025/6/27
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun HighlightTextPage() {
    Column(modifier = Modifier.background(PkColor.color_fill1)) {
        PkNavBar("高亮文本组件")
        // 示例1：基本用法（多个关键字）
        PkHighlightText(
            text = "Jetpack Compose 是 Android 的现代 UI 工具包",
            keywords = listOf("compose", "android"),
            highlightColor = Color.Blue,
            style = MaterialTheme.typography.body1
        )

        // 示例2：含特殊字符的关键字
        PkHighlightText(
            text = "价格：$199 (限时优惠)",
            keywords = listOf("$199", "（限时优惠）"),
            highlightColor = Color(0xFF4CAF50)
        )

        // 示例3：无关键字/空列表
        PkHighlightText(
            text = "Hello World",
            keywords = emptyList() // 显示普通文本
        )
    }
}