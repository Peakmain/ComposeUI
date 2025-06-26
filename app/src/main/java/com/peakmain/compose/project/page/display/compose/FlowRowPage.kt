package com.peakmain.compose.project.page.display.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.peakmain.compose.basic.BasicSize
import com.peakmain.compose.project.R
import com.peakmain.compose.ui.cell.PkCell
import com.peakmain.compose.ui.divier.PkDivider
import com.peakmain.compose.ui.flow.PkFlowRow
import com.peakmain.compose.ui.title.PkNavBar
import com.peakmain.compose.ui.title.PkTitle
import com.peakmain.compose.ui.title.PkTitleType

/**
 * author ：Peakmain
 * createTime：2025/6/26
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun FlowRowPage() {
    val tags = listOf(
        "Android",
        "Kotlin",
        "Jetpack Compose",
        "KMP",
        "Material Design",
        "UI",
        "Development"
    )
    var currentLine by remember {
        mutableStateOf(0)
    }
    var isExpand by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        PkNavBar("流式布局组件")
        Column(Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
            Column {
                PkTitle("默认限制2行", type = PkTitleType.TextBold1())
                PkFlowRow(
                    modifier = Modifier.padding(top = BasicSize.size_12),
                    horizontalSpacing = 8.dp,
                    verticalSpacing = 12.dp,
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

            Column(modifier = Modifier.padding(top = 40.dp)) {
                if (currentLine > 2) {
                    PkCell(
                        "超过2行显示展开/更多",
                        type = PkTitleType.TextBold1(),
                        rightText = if (!isExpand) "展开" else "收起",
                        rightIcon= if (isExpand) R.drawable.ic_expend_arrow_down else R.drawable.ic_expend_arrow_up,
                        rightClick = {
                            isExpand = !isExpand
                        }
                    )
                }
                PkFlowRow(
                    modifier = Modifier.padding(top = BasicSize.size_12),
                    horizontalSpacing = 8.dp,
                    verticalSpacing = 12.dp,
                    maxLine = if (isExpand) Int.MAX_VALUE else 2,
                    onLineCountChanged = {
                        currentLine = it
                    }) {
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
        }

    }
}