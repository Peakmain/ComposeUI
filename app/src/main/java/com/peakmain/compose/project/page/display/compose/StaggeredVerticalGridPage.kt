package com.peakmain.compose.project.page.display.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.peakmain.compose.project.component.CpColumn
import com.peakmain.compose.project.constants.PkColor
import com.peakmain.compose.ui.grid.PkStaggeredVerticalGrid
import kotlin.random.Random

/**
 * author ：Peakmain
 * createTime：2025/6/27
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun StaggeredVerticalGridPage() {
    // 生成示例数据（包含随机高度）
    val items = List(5) { index ->
        StaggeredItem(
            id = index, height = (20 + Random.nextInt(50)).dp, // 随机高度 (120-270dp)
            title = "Item ${index + 1}"
        )
    }
    CpColumn("瀑布流组件") {
        Column {
            PkStaggeredVerticalGrid(
                columns = 2,
                contentPadding = PaddingValues(16.dp),
                verticalItemSpacing = 4.dp,
                horizontalItemSpacing = 16.dp, // 水平间距
                modifier = Modifier.wrapContentHeight()

            ) {
                items.forEach { item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(item.height)
                            .background(
                                Color(
                                    red = Random.nextInt(256),
                                    green = Random.nextInt(256),
                                    blue = Random.nextInt(256),
                                    alpha = 255
                                )
                            ),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item.title,
                                color = Color.White,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

data class StaggeredItem(
    val id: Int, val height: Dp, val title: String
)