package com.peakmain.compose.ui.flow

/**
 * author ：Peakmain
 * createTime：2025/3/20
 * mail:2726449200@qq.com
 * describe：限制行数的FlowRow
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PkFlowRow(
    modifier: Modifier = Modifier,          // 布局修饰符
    horizontalSpacing: Dp = 0.dp,          // 子项水平间距
    verticalSpacing: Dp = 0.dp,            // 行间垂直间距
    maxLine: Int = 2,                      // 最大显示行数
    onLineCountChanged: ((Int) -> Unit)? = null, // 行数变化回调
    content: @Composable () -> Unit        // 子组件内容
) {
    val density = LocalDensity.current
    //当前行数的状态
    var lineCountState by remember { mutableStateOf(0) }

    //生命周期感知调用
    LaunchedEffect(lineCountState) {
        onLineCountChanged?.invoke(lineCountState)
    }
    SubcomposeLayout { constraints ->
        //测量所有的子View的测量数据
        val measurables = subcompose("measure", content)
        val hSpacingPx = with(density) { horizontalSpacing.roundToPx() }
        val vSpacingPx = with(density) { verticalSpacing.roundToPx() }
        //获取到所有测量结果
        val placeables =
            measurables.map { it.measure(constraints = constraints.copy(minWidth = 0)) }
        var currentLine = 0
        var currentRowWidth = 0
        var totalHeight = 0
        var currentRowHeight = 0
        placeables.forEachIndexed { index, placeable ->
            val itemWidth = placeable.width + if (index > 0) hSpacingPx else 0

            if (currentRowWidth + itemWidth > constraints.maxWidth) {
                currentLine++
                if (currentLine >= maxLine) return@forEachIndexed
                totalHeight += currentRowHeight + vSpacingPx
                currentRowWidth = 0
                currentRowHeight = 0
            }
            currentRowWidth += itemWidth
            currentRowHeight = maxOf(currentRowHeight, placeable.height)
        }
        lineCountState= if (currentRowWidth > 0) currentLine + 1 else currentLine
        //获取最大高度
        val maxHeight = totalHeight + currentRowHeight

        // 第二阶段：实际布局
        subcompose("content") {
            FlowRow(
                modifier = modifier
                    .heightIn(max = with(density) { maxHeight.toDp() })
                    .clipToBounds(),
                horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
                verticalArrangement = Arrangement.spacedBy(verticalSpacing)
            ) {
                content()
            }
        }.first().measure(constraints).let { placeable ->
            // 正确使用layout作用域
            layout(constraints.maxWidth, maxHeight) {
                placeable.place(0, 0)
            }
        }
    }
}

@Preview
@Composable
fun PkFlowRowPreview() {
    val tags = listOf("Android", "Kotlin", "Jetpack Compose", "KMP","Material Design", "UI", "Development")

    PkFlowRow(
        horizontalSpacing = 8.dp,
        verticalSpacing = 12.dp,
        maxLine = 2
    ) {
        tags.forEach { tag ->
            Text(
                text = tag,
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(16.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }
    }
}
