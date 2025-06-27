package com.peakmain.compose.project.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.peakmain.compose.ui.title.PkTitle
import com.peakmain.compose.ui.title.PkTitleType

/**
 * author ：Peakmain
 * createTime：2025/6/27
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun  CpTitle(title:String) {
    PkTitle(title, type = PkTitleType.TextBold1(), modifier = Modifier.padding(18.dp))
}