package com.peakmain.compose.project.page.display.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.peakmain.compose.project.page.basic.compose.NavBarPage
import com.peakmain.compose.space.PkSpacer
import com.peakmain.compose.ui.PkGridLayout
import com.peakmain.compose.ui.title.PkNavBar

/**
 * author ：Peakmain
 * createTime：2025/6/26
 * mail:2726449200@qq.com
 * describe：
 */
/**
 * 网格布局
 */
@Composable
fun GridPage() {
    val dataList = mutableListOf("A", "B", "C", "D", "E", "F", "G")

    Column(modifier = Modifier.padding(horizontal = 15.dp)) {
        PkNavBar("网格布局")
        // 基础用法（2列，带列间距和默认分割线）
        PkSpacer(isHorizontal = true)
        PkGridLayout(
            columns = 2,
            data = dataList,
            isShowHorizontalDivider = true,
            columnSpacing = 16.dp,
            divider = {
                PkSpacer(isHorizontal = true)
            },
        ) { index, item ->
            Text(
                text = "$item${index + 1}",
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()

            )
        }
    }
}