package com.peakmain.compose.ui

import androidx.annotation.IntRange
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * author ：Peakmain
 * createTime：2022/2/9
 * mail:2726449200@qq.com
 * describe：GridLayout的实现
 */
/**
 * @param columns 1行几个
 * @param data 源数据
 * @param isShowHorizontalDivider 是否显示水平分割线，默认不显示
 * @param content The content of the GridLayout
 */
@Composable
fun <E> PkGridLayout(
    @IntRange(from = 1) columns: Int,
    data: MutableList<E>,
    isShowHorizontalDivider: Boolean = false,
    divider: @Composable (() -> Unit)? = null,
    columnSpacing: Dp = 12.dp, // 新增参数，表示列间距，默认值为 8.dp
    content: @Composable (Int, E) -> Unit
) {
    val size = data.size
    val rows = (size + columns - 1) / columns
    Column {
        for (rowIndex in 0 until rows) {
            Row(horizontalArrangement = Arrangement.spacedBy(columnSpacing)) {
                for (columnIndex in 0 until columns) {
                    val itemIndex = rowIndex * columns + columnIndex
                    if (itemIndex < size) {
                        Box(Modifier.weight(1f)) {
                            content(itemIndex, data[itemIndex])
                        }
                    } else {
                        Spacer(
                            Modifier
                                .weight(1f)
                        )
                    }
                }
            }
            if (isShowHorizontalDivider && rowIndex < (rows - 1)) {
                if (divider != null) {
                    divider()
                } else {
                    Divider(thickness = 0.5.dp)
                }
            }
        }
    }
}