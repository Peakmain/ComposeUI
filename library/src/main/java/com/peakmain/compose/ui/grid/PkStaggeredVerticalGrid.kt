package com.peakmain.compose.ui.grid

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * author ：Peakmain
 * createTime：2025/6/21
 * mail:2726449200@qq.com
 * describe：瀑布流，非懒加载版
 */
/**
 * 适合中小数据（<50项）,若超过50，请用LazyVerticalStaggeredGrid
 * @param columns 网格列数（必须 >0）
 * @param modifier 容器修饰符（背景、尺寸等）
 * @param contentPadding 内容区域的内边距
 * @param verticalItemSpacing 垂直方向（行间）间距,默认是8.dp
 * @param horizontalItemSpacing 水平方向（列间）间距,默认是8.dp
 * @param content 网格项内容（任意 Composable 组件）
 */
@Composable
fun PkStaggeredVerticalGrid(
    columns: Int,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalItemSpacing: Dp = 8.dp,
    horizontalItemSpacing: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    require(columns > 0) { "列数必须大于0" }

    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        // 计算内容区域宽度（减去内边距）
        val horizontalPadding = contentPadding.calculateLeftPadding(layoutDirection) +
                contentPadding.calculateRightPadding(layoutDirection)
        val contentWidth = (constraints.maxWidth - horizontalPadding.roundToPx()).coerceAtLeast(0)

        // 计算列宽（减去水平间距）
        val columnWidth = ((contentWidth - (columns - 1) * horizontalItemSpacing.roundToPx()) / columns)
            .coerceAtLeast(0)

        // 初始化列高度数组
        val columnHeights = IntArray(columns) { contentPadding.calculateTopPadding().roundToPx() }

        // 测量所有子项
        val placeables = measurables.map { measurable ->
            // 约束子项宽度为列宽
            val placeable = measurable.measure(
                constraints.copy(
                    minWidth = columnWidth,
                    maxWidth = columnWidth
                )
            )
            placeable
        }

        // 计算每个子项的位置
        val positions = mutableListOf<Pair<Int, Int>>()

        placeables.forEach { placeable ->
            // 找到当前高度最小的列
            val minColumn = columnHeights.withIndex().minByOrNull { it.value }?.index ?: 0
            val x = contentPadding.calculateLeftPadding(layoutDirection).roundToPx() +
                    minColumn * (columnWidth + horizontalItemSpacing.roundToPx())
            val y = columnHeights[minColumn]

            // 更新列高度（加上当前项高度和垂直间距）
            columnHeights[minColumn] = y + placeable.height + verticalItemSpacing.roundToPx()

            positions.add(x to y)
        }

        // 计算总高度（最高列的高度 + 底部内边距）
        val totalHeight = (columnHeights.maxOrNull() ?: 0) +
                contentPadding.calculateBottomPadding().roundToPx() -
                verticalItemSpacing.roundToPx() // 减去最后一项的额外间距

        layout(constraints.maxWidth, totalHeight) {
            placeables.forEachIndexed { index, placeable ->
                val (x, y) = positions[index]
                placeable.place(x, y)
            }
        }
    }
}
