package com.peakmain.compose.project.page.display.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.peakmain.compose.project.constants.PkColor
import com.peakmain.compose.ui.divier.PkDivider
import com.peakmain.compose.ui.title.PkNavBar
import com.peakmain.compose.ui.title.PkTitle
import com.peakmain.compose.ui.title.PkTitleType

/**
 * author ：Peakmain
 * createTime：2025/6/26
 * mail:2726449200@qq.com
 * describe：分割线组件
 */
@Composable
fun DividerPage() {
    Column(Modifier
        .background(PkColor.color_brand2)
        .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
        PkNavBar("分割线组件")
        Column {
            PkTitle("默认垂直实线分割线", type = PkTitleType.TextBold1())
            PkDivider()
        }
        Column {
            PkTitle("水平实线", type = PkTitleType.TextBold1())
            PkDivider(modifier = Modifier.padding(top = 10.dp), isHorizontal = true)
        }
        Column {
            PkTitle("垂直虚线", type = PkTitleType.TextBold1())
            PkDivider(isDash = true)
        }
        Column {
            PkTitle("水平虚线", type = PkTitleType.TextBold1())
            PkDivider(modifier = Modifier.padding(top = 10.dp), isHorizontal = true, isDash = true)
        }
    }
}