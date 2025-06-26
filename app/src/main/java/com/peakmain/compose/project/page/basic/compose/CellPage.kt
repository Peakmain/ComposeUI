package com.peakmain.compose.project.page.basic.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.peakmain.compose.project.constants.PkColor
import com.peakmain.compose.ui.cell.PkCell
import com.peakmain.compose.ui.title.PkNavBar
import com.peakmain.compose.ui.title.PkTitleType

/**
 * author ：Peakmain
 * createTime：2025/6/26
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun CellPage() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.background(PkColor.color_fill2)
    ) {
        PkNavBar("单元格组件")
        PkCell(
            "常用功能",
            PkTitleType.BigTitle3(),
            rightText = "展开",
            modifier = Modifier
                .padding(horizontal = 18.dp),
            color = Color(0xFF14401B)
        )
        // 基础用法（右侧带箭头图标）
        PkCell(
            text = "个人资料",
            type = PkTitleType.TitleBold1(),
            modifier = Modifier.padding(16.dp),
            rightContent = {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "进入",
                    tint = Color.Gray
                )
            }
        )

        // 复杂右侧内容（文字 + 开关）
        PkCell(
            text = "夜间模式",
            color = Color.Black,
            rightContent = {
                Text("已开启", color = Color.Gray)
                Spacer(Modifier.width(8.dp))
                Switch(
                    checked = true,
                    onCheckedChange = { /* 状态更新逻辑 */ }
                )
            }
        )
    }
}